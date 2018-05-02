package b12app.vyom.com.flowit.tabfragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import b12app.vyom.com.flowit.dialog.*;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.tabfragment.subtaskedit.SubTaskEditContract;
import b12app.vyom.com.flowit.tabfragment.subtaskedit.SubTaskEditPresenter;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jinliyu on 5/1/18.
 */

public class FragmentSubTaskEdit extends Fragment implements SubTaskEditContract.IView, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    @BindView(R.id.subtask_edit_container)
    CoordinatorLayout taskEditContainer;

    @BindView(R.id.tv_subtask_detail_id)
    TextView taskDetailId;

    @BindView(R.id.edt_subtask_detail_name)
    TextView nameEdt;

    @BindView(R.id.spn_subtask_detail_status)
    Spinner statusSpr;

    @BindView(R.id.tv_subtask_detail_desc)
    TextView descEdt;

    @BindView(R.id.tv_subtask_detail_due_date)
    TextView dateTv;

    @BindView(R.id.layout_subtask_detail_flow_task)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.fab_detail_subtask)
    FloatingActionButton editFloatBtn;

    @BindView(R.id.imgbtn_add_member_to_subtask)
    ImageButton addMemberBtn;

    @BindView(R.id.ll_member)
    LinearLayout memberLl;

    @Inject
    DataManager mDataManager;


    private DatePickerDialog toDatePickerDialog;

    private Unbinder unbinder;
    private static boolean FLAG_EDIT_MODE = false;
    private GeneralSubTask.ProjectsubtaskBean subtaskNode;
    private SubTaskEditContract.IPresenter subtaskEdtPresenter;

    List<Employee.EmployeesBean> memberList;

    int[] urls = {R.drawable.ic_avatar};

    private static final String TAG = "FragmentProjectEdit";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_subtask_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        //dagger2 inject
        AppApplication.get(getContext())
                .getAppComponent()
                .inject(this);


        //initializing IPresenter
        subtaskEdtPresenter = new SubTaskEditPresenter(mDataManager, this);

        subtaskEdtPresenter.getData(getArguments());

        return view;
    }

    @Override
    public void setPresenter(SubTaskEditContract.IPresenter presenter) {

    }

    @Override
    public void initView(Parcelable subtaskParc) {
        this.subtaskNode = (GeneralSubTask.ProjectsubtaskBean) subtaskParc;
        taskDetailId.setText("SubTask#: " + subtaskNode.getTaskid());
        nameEdt.setText(subtaskNode.getSubtaskname());
        descEdt.setText(subtaskNode.getSubtaskdesc());
        statusSpr.setSelection(Integer.valueOf(subtaskNode.getSubtaskstatus()) - 1);
        dateTv.setText(getString(R.string.feng_due_date) + "  " + subtaskNode.getEndstart());
        editFloatBtn.setTag(FLAG_EDIT_MODE);

        //disable edit mode
        enableEdit(false);

        initFlow();

        //fireBase database
        subtaskEdtPresenter.initFireDb(subtaskNode);

        //date picker
        Calendar newCalendar = Calendar.getInstance();
        toDatePickerDialog = new DatePickerDialog(getContext(), this, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        //click listener
        editFloatBtn.setOnClickListener(this);
        dateTv.setOnClickListener(this);
        addMemberBtn.setOnClickListener(this);
        memberLl.setOnClickListener(this);
    }

    private void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(getActivity()).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }

    }

    private void enableEdit(boolean enable) {
        statusSpr.setEnabled(enable);
        myFlowlayout.setEnabled(enable);
    }

    @Override
    public void updateEndDate(String dateEndString) {
        Toast.makeText(getActivity(), dateEndString, Toast.LENGTH_SHORT).show();
        dateTv.setText(dateEndString);
    }

    @Override
    public void changeEditMode(boolean flagEditMode, GeneralSubTask.ProjectsubtaskBean subtaskNode) {
        FLAG_EDIT_MODE = flagEditMode;
        if (flagEditMode) {
            enableEdit(flagEditMode);
            addMemberBtn.setVisibility(View.VISIBLE);
            editFloatBtn.setImageResource(R.drawable.ic_correct);
        } else {
            enableEdit(flagEditMode);
            addMemberBtn.setVisibility(View.GONE);
            editFloatBtn.setImageResource(R.drawable.ic_edit);

            subtaskEdtPresenter.updateSubTask(subtaskNode);
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//        Snackbar.make(taskEditContainer, msg, Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        subtaskEdtPresenter.datePickerClick(year, month, dayOfMonth, subtaskNode);
    }

    @Override
    public void updateMembList(List<Employee.EmployeesBean> list) {
        memberList = list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_detail_subtask:
                subtaskEdtPresenter.editFloatBtnClick(v, FLAG_EDIT_MODE, nameEdt, statusSpr, descEdt, subtaskNode);

                break;

            case R.id.imgbtn_add_member_to_subtask:

                SubEmployeeListFragmentDialog employeeListFragmentDialog = new SubEmployeeListFragmentDialog();
                Bundle bundle = new Bundle();
                bundle.putParcelable("subtasknode", subtaskNode);
                employeeListFragmentDialog.setArguments(bundle);
                employeeListFragmentDialog.show(getFragmentManager(), "emp dialog");

                //       apiService.assignTask(taskNode.getTaskid(),taskNode.getProjectid(),)
                break;
            case R.id.ll_member:
                if (memberList.size() < 1 || memberList == null) {
                    Toast.makeText(getContext(), "No team member yet", Toast.LENGTH_SHORT).show();
                } else {
                    MemberDialog memberDialog = MemberDialog.newInstance(memberList);
                    memberDialog.showDialog(getActivity().getSupportFragmentManager(), "memberDlg");
                }
                break;
        }
    }

}
