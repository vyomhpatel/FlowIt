package b12app.vyom.com.flowit.subtaskcreate;

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
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.task.TaskCreateActivity;
import b12app.vyom.com.flowit.task.TaskCreatePresenter;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SubTaskCreateActivity extends AppCompatActivity implements View.OnClickListener, SubTaskContract.IView, TaskListFragmentDialog.OnCompleteListener{
    private  DataManager dataManager;
    private  SubTaskContract.IPresenter iPresenter;
    private String dateStartString, dateEndString;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    String received_task_id;

    @BindView(R.id.task_create_container)
    LinearLayout task_create_container;


    @BindView(R.id.layout_flow_task)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.edt_subtask_name)
    EditText edt_subtask_name;

    @BindView(R.id.edt_subtask_description)
    EditText edt_subtask_description;

    @BindView(R.id.tv_task_start_date)
    TextView tv_task_start_date;

    @BindView(R.id.tv_task_end_date)
    TextView tv_task_end_date;

    @BindView(R.id.tv_add_subtask)
    TextView tv_add_subtask;

    @BindView(R.id.tb)
    Toolbar toolbar;


    int[] urls = {R.drawable.ic_avatar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_task_create);

        ButterKnife.bind(this);



        //initializing data manager
//        dataManager = new DataManager();
        iPresenter = new SubTaskPresenter(dataManager,SubTaskCreateActivity.this);


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        tv_task_start_date.setOnClickListener(this);
        tv_task_end_date.setOnClickListener(this);
        tv_add_subtask.setOnClickListener(this);


        initToolerBar();

        initFlow();

        setDateTimeField();
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

    private void initFlow() {

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(this).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }

    private void initToolerBar() {
//        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.blue0));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(R.string.create_subtask);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attachment, menu);
        return true;
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
            case R.id.tv_add_subtask:
                displayProjectFragment();
        }
    }

    private void displayProjectFragment() {
        FragmentManager manager = getFragmentManager();
        TaskListFragmentDialog dialog = new TaskListFragmentDialog();
        dialog.show(manager,"task list");
    }

    @Override
    public void setPresenter(SubTaskContract.IPresenter presenter) {
        iPresenter = presenter;
    }

    @Override
    public void displaySnackbar() {
        Snackbar.make(task_create_container,"Task Created Successfully!",Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
    }


    @Override
    public void onComplete(String task_id, String task_name) {

        tv_add_subtask.setText(task_name);
        received_task_id = task_id;
    }


    public void addAssignee(View view) {
        Snackbar.make(task_create_container,"Sub Task Created Successfully!",Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        }).setActionTextColor(getResources().getColor(R.color.sbRed)).show();
    }



    public void createsubTask(View view) {

        GeneralSubTask.ProjectsubtaskBean subtask = new GeneralSubTask.ProjectsubtaskBean(received_task_id,"18","18", edt_subtask_name.getText().toString()
               ,"1" , edt_subtask_description.getText().toString(),dateStartString,dateEndString);
        iPresenter.onSubTaskCreateButtonClick(view,subtask);
    }
}
