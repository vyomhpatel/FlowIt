package b12app.vyom.com.flowit.tabfragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import javax.inject.Inject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.daggerUtils.AppApplication;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.dialog.EmployeeListFragmentDialog;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.taskedit.TaskEditContract;
import b12app.vyom.com.flowit.tabfragment.taskedit.TaskEditPresenter;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTaskEdit extends Fragment implements TaskEditContract.IView, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    @BindView(R.id.task_edit_container)
    CoordinatorLayout taskEditContainer;

    @BindView(R.id.tv_task_detail_id)
    TextView taskDetailId;

    @BindView(R.id.edt_task__detail_name)
    TextView nameEdt;

    @BindView(R.id.spn_task_detail_status)
    Spinner statusSpr;

    @BindView(R.id.tv_task_detail_desc)
    TextView descEdt;

    @BindView(R.id.tv_task_detail_due_date)
    TextView dateTv;

    @BindView(R.id.layout_task_detail_flow_task)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.fab_detail_task)
    FloatingActionButton editFloatBtn;

    @BindView(R.id.imgbtn_add_member_to_task)
    ImageButton addMemberBtn;

    @Inject
    DataManager mDataManager;


    private DatePickerDialog toDatePickerDialog;

    private Unbinder unbinder;
    private static boolean FLAG_EDIT_MODE = false;
    private GeneralTask.ProjecttaskBean taskNode;
    private TaskEditContract.IPresenter taskEdtPresenter;

    int[] urls = {R.drawable.ic_avatar};

    private static final String TAG = "FragmentProjectEdit";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_task_detail, container, false);
        unbinder = ButterKnife.bind(this, v);

        //dagger2 inject
        AppApplication.get(getContext())
                .getAppComponent()
                .inject(this);


        //initializing IPresenter
        taskEdtPresenter = new TaskEditPresenter(mDataManager, this);

        taskEdtPresenter.getData(getArguments());




        return v;
    }


    public void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(getActivity()).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void enableEdit(Boolean enable) {
        statusSpr.setEnabled(enable);
        myFlowlayout.setEnabled(enable);
    }


    @Override
    public void initView(Parcelable taskParc) {
        this.taskNode = (GeneralTask.ProjecttaskBean) taskParc;
        taskDetailId.setText("Task ID: " + taskNode.getTaskid());
        nameEdt.setText(taskNode.getTaskname());
        descEdt.setText(taskNode.getTaskdesc());
        statusSpr.setSelection(Integer.valueOf(taskNode.getTaskstatus()) - 1);
        dateTv.setText(getString(R.string.feng_due_date) + "  " + taskNode.getEndstart());
        editFloatBtn.setTag(FLAG_EDIT_MODE);

        //disable edit mode
        enableEdit(false);

        initFlow();

        //date picker
        Calendar newCalendar = Calendar.getInstance();
        toDatePickerDialog = new DatePickerDialog(getContext(), this, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        //click listener
        editFloatBtn.setOnClickListener(this);
        dateTv.setOnClickListener(this);
        addMemberBtn.setOnClickListener(this);

    }

    @Override
    public void updateEndDate(String dateEndString) {
        Toast.makeText(getActivity(), dateEndString, Toast.LENGTH_SHORT).show();
        dateTv.setText(dateEndString);
    }

    @Override
    public void changeEditMode(boolean flagEditMode) {
        FLAG_EDIT_MODE = flagEditMode;
        if (flagEditMode) {
            enableEdit(flagEditMode);
            addMemberBtn.setVisibility(View.VISIBLE);
            editFloatBtn.setImageResource(R.drawable.ic_correct);
        } else {
            enableEdit(flagEditMode);
            addMemberBtn.setVisibility(View.GONE);
            editFloatBtn.setImageResource(R.drawable.ic_edit);

            taskEdtPresenter.updateTask(taskNode);
        }
    }

    @Override
    public void showToast(String msg) {
        Snackbar.make(taskEditContainer, msg, Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setPresenter(TaskEditContract.IPresenter presenter) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        taskEdtPresenter.datePickerClick(year, month, dayOfMonth, taskNode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_detail_task:
                taskEdtPresenter.editFloatBtnClick(v, FLAG_EDIT_MODE, nameEdt, statusSpr, descEdt, taskNode);

                break;
            case R.id.tv_task_detail_due_date:

                toDatePickerDialog.show();

                break;

            case R.id.imgbtn_add_member_to_task:

                EmployeeListFragmentDialog employeeListFragmentDialog = new EmployeeListFragmentDialog();
                Bundle bundle = new Bundle();
                bundle.putString("task_id", taskNode.getTaskid());
                bundle.putString("project_id", taskNode.getProjectid());
                employeeListFragmentDialog.setArguments(bundle);
                employeeListFragmentDialog.show(getFragmentManager(), "emp dialog");

                    //   apiService.assignTask(taskNode.getTaskid(),taskNode.getProjectid());
                break;
        }
    }


}