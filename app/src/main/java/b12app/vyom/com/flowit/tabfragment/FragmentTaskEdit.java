package b12app.vyom.com.flowit.tabfragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.daggerUtils.AppApplication;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.dialog.EmployeeListFragmentDialog;
import b12app.vyom.com.flowit.dialog.MemberDialog;
import b12app.vyom.com.flowit.dialog.TeamDialog;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.taskedit.TaskEditContract;
import b12app.vyom.com.flowit.tabfragment.taskedit.TaskEditPresenter;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.FbHelper;
import b12app.vyom.com.utils.MyFlowlayout;
import b12app.vyom.com.utils.SpHelper;
import b12app.vyom.com.utils.StatusHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTaskEdit extends Fragment implements TaskEditContract.IView, DatePickerDialog.OnDateSetListener, View.OnClickListener, TeamDialog.OnCompleteListener, EmployeeListFragmentDialog.OnCompleteListener {

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

    @BindView(R.id.ll_task_member)
    LinearLayout memberLl;

    @Inject
    DataManager mDataManager;

    @Inject
    SharedPreferences sp;

    private List<Employee.EmployeesBean> memberList;

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
    public void updateMembList(List<Employee.EmployeesBean> list) {
        memberList = list;
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

        //fireBase database
        taskEdtPresenter.initFireDb(taskNode);

        //click listener
        editFloatBtn.setOnClickListener(this);
        addMemberBtn.setOnClickListener(this);
        memberLl.setOnClickListener(this);
    }

    @Override
    public void changeEditMode(boolean flagEditMode) {
        FLAG_EDIT_MODE = flagEditMode;
        if (flagEditMode) {
            enableEdit(flagEditMode);
            if (SpHelper.getUserType(sp).equals(Global.MANAGER)) {
                addMemberBtn.setVisibility(View.VISIBLE);
            }
            editFloatBtn.setImageResource(R.drawable.ic_correct);
        } else {
            enableEdit(flagEditMode);
            addMemberBtn.setVisibility(View.GONE);
            editFloatBtn.setImageResource(R.drawable.ic_edit);

            //udpate status
            taskNode.setTaskstatus(statusSpr.getSelectedItemPosition() + 1 + "");
            //go web request
            taskEdtPresenter.updateTask(taskNode);
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TaskEditContract.IPresenter presenter) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_detail_task:
                taskEdtPresenter.editFloatBtnClick(v, FLAG_EDIT_MODE, nameEdt, statusSpr, descEdt, taskNode);

                break;

            case R.id.ll_task_member:
                if (memberList == null || memberList.size() < 1) {
                    Toast.makeText(getContext(), R.string.no_member, Toast.LENGTH_SHORT).show();
                } else {
                    MemberDialog memberDialog = MemberDialog.newInstance(memberList);
                    memberDialog.showDialog(getActivity().getSupportFragmentManager(), Global.MEMBER_DLG);
                }
                break;

            case R.id.imgbtn_add_member_to_task:

                EmployeeListFragmentDialog employeeListFragmentDialog = new EmployeeListFragmentDialog();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Global.TASK_NODE, taskNode);
                employeeListFragmentDialog.setArguments(bundle);
                employeeListFragmentDialog.show(getFragmentManager(), Global.EMP_DLG);

                employeeListFragmentDialog.setListener(this);
                break;
        }
    }


    @Override
    public void onComplete(List<Employee.EmployeesBean> employeeIdList) {

    }

    @Override
    public void onComplete(int position, List<Employee.EmployeesBean> list) {
        taskEdtPresenter.addTaskMember(position, list, taskNode);
    }
}