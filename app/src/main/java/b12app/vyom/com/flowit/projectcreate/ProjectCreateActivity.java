package b12app.vyom.com.flowit.projectcreate;

import android.app.DatePickerDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import b12app.vyom.com.flowit.home.BaseActivity;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectCreateActivity extends BaseActivity implements View.OnClickListener, ProjectCreateContract.IView {

    private static final String TAG = "project create";
    public static final String DATE = "date: ";
    public static final String DB_AUTH_LOG = "DbAuthLog";
    public static final String ERROR_AUTHENTICATING = "Error authenticating";
    public static final String PROJECTSTATUS = "1";

    private Calendar calendar;
    private int year, month, day;
    private String dateStartString, dateEndString;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ProjectCreateContract.IPresenter iPresenterProject;

    @BindView(R.id.container_project_create)
    CoordinatorLayout container_project_create;

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

    @Inject
    DataManager dataManager;

    private DropboxAPI<AndroidAuthSession> mDBApi;

    int[] urls = {R.drawable.ic_avatar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat(Global.YYYY_MM_DD, Locale.US);

        //initializing data manager
        iPresenterProject = new ProjectCreatePresenter(dataManager, ProjectCreateActivity.this);

        initToolBar();

        initFlow();

        setDateTimeField();

        tv_start_date.setOnClickListener(this);
        tv_end_date.setOnClickListener(this);
        myFlowlayout.setOnClickListener(this);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(ProjectCreateActivity.this);
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
        if (TextUtils.isEmpty(edt_project_name.getText().toString()) || TextUtils.isEmpty(etProjectDescription.getText().toString())
                || TextUtils.isEmpty(dateStartString) || TextUtils.isEmpty(dateEndString)) {
            Toast.makeText(this, Global.TEXT_FIELD_CAN_NOT_BE_NULL, Toast.LENGTH_SHORT).show();
        }

            Project.ProjectsBean project = new Project.ProjectsBean(edt_project_name.getText().toString(), PROJECTSTATUS, etProjectDescription.getText().toString(),
                    dateStartString, dateEndString);
        iPresenterProject.onProjectCreateButtonClick(view, project);
        Log.i(TAG, DATE + dateStartString + " " + dateEndString);
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
                Snackbar.make(container_project_create, Global.MEMBERS_CAN_T_BE_ADDED_WHILE_CREATING_PROJECT, Snackbar.LENGTH_SHORT).setAction(Global.OK, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
        }
    }

    @Override
    public void setPresenter(ProjectCreateContract.IPresenter presenter) {
        //to connect iView to the presenter.
        iPresenterProject = presenter;
    }

    @Override
    public void displaySnackbar() {
        Snackbar.make(container_project_create, Global.PROJECT_CREATED_SUCCESSFULLY, Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(R.color.colorAccent)).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_attach:
                // In the class declaration section:
                // And later in some initialization function:
                AppKeyPair appKeys = new AppKeyPair(Global.APP_KEY, Global.APP_SECRET);
                AndroidAuthSession session = new AndroidAuthSession(appKeys);
                mDBApi = new DropboxAPI<>(session);

                // MyActivity below should be your activity class name
                mDBApi.getSession().startOAuth2Authentication(ProjectCreateActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDBApi != null) {
            if (mDBApi.getSession().authenticationSuccessful()) {
                try {
                    // Required to complete auth, sets the access token on the session
                    mDBApi.getSession().finishAuthentication();

                    String accessToken = mDBApi.getSession().getOAuth2AccessToken();
                } catch (IllegalStateException e) {
                    Log.i(DB_AUTH_LOG, ERROR_AUTHENTICATING, e);
                }
            }
        }
    }
}
