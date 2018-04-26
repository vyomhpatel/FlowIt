package b12app.vyom.com.flowit.task;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.projectcreate.ProjectCreateActivity;
import b12app.vyom.com.flowit.projectcreate.ProjectCreatePresenter;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskCreateActivity extends AppCompatActivity implements View.OnClickListener, TaskContract.IView,ProjectListFragmentDialog.OnCompleteListener{

    private String dateStartString, dateEndString;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private DataManager dataManager;
    private TaskContract.IPresenter iPresenterTask;
    private TaskContract.IPresenter iPresenter;

    @BindView(R.id.task_create_container)
    LinearLayout task_create_container;


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




    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        ButterKnife.bind(this);


        //initializing data manager
        dataManager = new DataManager();
        iPresenterTask = new TaskCreatePresenter(dataManager,TaskCreateActivity.this);


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        tv_task_start_date.setOnClickListener(this);
        tv_task_end_date.setOnClickListener(this);
        tv_add_project.setOnClickListener(this);


        initToolerBar();

        initFlow();

        setDateTimeField();
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

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateEndString = String.valueOf(dateFormatter.format(newDate.getTime()));
                tv_task_end_date.setText(dateFormatter.format(newDate.getTime()));
                // dateEndString = dateFormatter.format(newDate.getTime());

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    public void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(this).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attachment, menu);
        return true;
    }

    public void addAssignee(View view) {
        Snackbar.make(task_create_container,"Task Created Successfully!",Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
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
        dialog.show(manager,"project list");
    }

    @Override
    public void displaySnackbar() {
        Snackbar.make(task_create_container,"Task Created Successfully!",Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
    }

    @Override
    public void setPresenter(TaskContract.IPresenter presenter) {
            iPresenterTask = presenter;
    }

    public void createTask(View view) {

    }


    @Override
    public void onComplete(String project_id, String project_name) {

        tv_add_project.setText(project_name);
    }
}
