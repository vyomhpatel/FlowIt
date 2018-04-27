package b12app.vyom.com.flowit.projectcreate;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectCreateActivity extends AppCompatActivity implements View.OnClickListener, ProjectContract.IView {

    private static final String TAG = "project create";

    private Calendar calendar;
    private int year, month, day;
    private String dateStartString, dateEndString;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ProjectContract.IPresenter iPresenterProject;
    private DataManager dataManager;


    @BindView(R.id.container_project_create)
    LinearLayout container_project_create;

    @BindView(R.id.layout_flow)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.tb)
    Toolbar toolbar;

    @BindView(R.id.tv_start_date)
    TextView tv_start_date;

    @BindView(R.id.tv_end_date)
    TextView tv_end_date;

    @BindView(R.id.fab_create_project)
    android.support.design.widget.FloatingActionButton fab_create_project;

    @BindView(R.id.etProjectDescription)
    EditText etProjectDescription;

    @BindView(R.id.edt_project_name)
    EditText edt_project_name;


    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        ButterKnife.bind(this);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        // iPresenterProject = new ProjectCreatePresenter();

        tv_start_date.setOnClickListener(this);
        tv_end_date.setOnClickListener(this);
        myFlowlayout.setOnClickListener(this);


        //initializing data manager
        dataManager = new DataManager();
        iPresenterProject = new ProjectCreatePresenter(dataManager, ProjectCreateActivity.this);

        initToolBar();
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
                tv_start_date.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateEndString = String.valueOf(dateFormatter.format(newDate.getTime()));
                tv_end_date.setText(dateFormatter.format(newDate.getTime()));
                // dateEndString = dateFormatter.format(newDate.getTime());

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    private void initToolBar() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.green2));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.create_project);
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

    public void createProject(View view) {

        Project.ProjectsBean project = new Project.ProjectsBean(edt_project_name.getText().toString(),"1",etProjectDescription.getText().toString(),
                dateStartString,dateEndString);
        iPresenterProject.onProjectCreateButtonClick(view, project);
        Log.i(TAG, "date: " + dateStartString + " " + dateEndString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_date:
                fromDatePickerDialog.show();
                break;
            case R.id.tv_end_date:
                toDatePickerDialog.show();
                break;
            case R.id.layout_flow:
                Snackbar.make(container_project_create, "Members can't be added while creating project", Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
        }
    }

    @Override
    public void setPresenter(ProjectContract.IPresenter presenter) {
        //to connect iview to the presenter.
        iPresenterProject = presenter;
    }

    @Override
    public void displaySnackbar() {
        Snackbar.make(container_project_create, "Project Created Successfully!", Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(R.color.colorAccent)).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }
}
