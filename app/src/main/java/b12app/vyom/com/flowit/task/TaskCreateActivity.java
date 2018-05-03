package b12app.vyom.com.flowit.task;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.daggerUtils.AppComponent;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.dialog.ProjectListFragmentDialog;
import b12app.vyom.com.flowit.home.BaseActivity;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.subtaskcreate.SubTaskCreateActivity;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskCreateActivity extends BaseActivity implements TaskCreateContract.IView, View.OnClickListener, ProjectListFragmentDialog.OnCompleteListener {
    private String dateStartString, dateEndString;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TaskCreateContract.IPresenter iPresenter;
    private String received_project_id;
    @Inject
    DataManager dataManager;

    @BindView(R.id.task_create_container)
    CoordinatorLayout task_create_container;


    @BindView(R.id.layout_flow_task)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.edt_task_name)
    EditText edt_task_name;

    @BindView(R.id.edt_task_description)
    EditText edt_task_description;

    @BindView(R.id.tv_task_start_date)
    TextView tv_task_start_date;

    @BindView(R.id.tv_task_end_date)
    TextView tv_task_end_date;

    @BindView(R.id.tv_add_project)
    TextView tv_add_project;

    @BindView(R.id.tb)
    Toolbar toolbar;
    private DropboxAPI<AndroidAuthSession> mDBApi;

    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        ButterKnife.bind(this);

        //initializing data manager

        iPresenter = new TaskCreatePresenter(dataManager, TaskCreateActivity.this);


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        tv_task_start_date.setOnClickListener(this);
        tv_task_end_date.setOnClickListener(this);
        tv_add_project.setOnClickListener(this);


        initToolerBar();

        initFlow();

        setDateTimeField();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void initToolerBar() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.blue0));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.create_task);
    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateStartString = String.valueOf(dateFormatter.format(newDate.getTime()));
                tv_task_start_date.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateEndString = String.valueOf(dateFormatter.format(newDate.getTime()));
                tv_task_end_date.setText(dateFormatter.format(newDate.getTime()));
                // dateEndString = dateFormatter.format(newDate.getTime());

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    public void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(this).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }


    public void addAssignee(View view) {
        Snackbar.make(task_create_container, "Task Created Successfully!", Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).setActionTextColor(getResources().getColor(R.color.sbRed)).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_task_start_date:
                fromDatePickerDialog.show();
                break;
            case R.id.tv_task_end_date:
                toDatePickerDialog.show();
                break;
            case R.id.tv_add_project:
                displayProjectFragment();
        }
    }

    private void displayProjectFragment() {

        FragmentManager manager = getFragmentManager();
        ProjectListFragmentDialog dialog = new ProjectListFragmentDialog();
        dialog.setListener(this);

        dialog.show(manager, "project list");
    }

    @Override
    public void displaySnackbar() {
        Snackbar.make(task_create_container, "Task Created Successfully!", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskCreateActivity.this, HomeActivity.class));
                finish();
            }
        }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
    }

    @Override
    public void setPresenter(TaskCreateContract.IPresenter presenter) {
        iPresenter = presenter;
    }

    public void createTask(View view) {
        GeneralTask.ProjecttaskBean task = new GeneralTask.ProjecttaskBean(received_project_id, edt_task_name.getText().toString()
                , "1", edt_task_description.getText().toString(), dateStartString, dateEndString);
        iPresenter.onTaskCreateButtonClick(view, task);
    }


    @Override
    public void onComplete(String project_id, String project_name) {

        tv_add_project.setText(project_name);
        received_project_id = project_id;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(TaskCreateActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.menu_attach:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TaskCreateActivity.this, HomeActivity.class));
        finish();
    }
}
