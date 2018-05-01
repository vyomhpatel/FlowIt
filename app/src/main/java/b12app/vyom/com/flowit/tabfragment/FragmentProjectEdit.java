package b12app.vyom.com.flowit.tabfragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import b12app.vyom.com.flowit.dialog.MemberDialog;
import b12app.vyom.com.flowit.dialog.TeamDialog;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.tabfragment.projectedit.ProjectEditContract;
import b12app.vyom.com.flowit.tabfragment.projectedit.ProjectEditPresenter;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.FbHelper;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName FragmentProjectEdit
 * @Date 4/26/18, 3:58 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentProjectEdit extends Fragment implements ProjectEditContract.IView, DatePickerDialog.OnDateSetListener, View.OnClickListener, TeamDialog.OnCompleteListener {
    @BindView(R.id.tv_detail_id)
    TextView projectIdTv;

    @BindView(R.id.edt_detail_name)
    TextView nameEdt;

    @BindView(R.id.edt_detail_status)
    Spinner statusSpr;

    @BindView(R.id.tv_detail_desc)
    TextView descEdt;

    @BindView(R.id.tv_detail_date)
    TextView dateTv;

    @BindView(R.id.layout_detail_flow)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.ll_member)
    LinearLayout memberLl;

    @BindView(R.id.fab_detail_project)
    FloatingActionButton editFloatBtn;

    @BindView(R.id.imgbtn_add_member)
    ImageButton addMemberBtn;

    @Inject
    DataManager dataManager;

    private DatePickerDialog toDatePickerDialog;
    private Project.ProjectsBean projectNode;
    private List<Employee.EmployeesBean> memberList;
    private ProjectEditPresenter projectEditPresenter;

    private Unbinder unbinder;
    private static boolean FLAG_EDIT_MODE = false;

    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar};

    private static final String TAG = "FragmentProjectEdit";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_project_detail, container, false);
        unbinder = ButterKnife.bind(this, v);

        //dagger2 inject
        AppApplication.get(getContext())
                .getAppComponent()
                .inject(this);

        projectEditPresenter = new ProjectEditPresenter(dataManager, this);

        projectEditPresenter.getData(getArguments());

        return v;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void enableEdit(Boolean enable) {
        nameEdt.setEnabled(enable);
        statusSpr.setEnabled(enable);
        descEdt.setEnabled(enable);
        myFlowlayout.setEnabled(enable);
        dateTv.setEnabled(enable);
    }

    @Override
    public void setPresenter(ProjectEditContract.IPresenter presenter) {

    }

    @Override
    public void initView(Project.ProjectsBean projectNode) {
        this.projectNode = projectNode;

        projectIdTv.setText(getString(R.string.feng_project_id) + "  " + projectNode.getId());
        nameEdt.setText(projectNode.getProjectname());
        descEdt.setText(projectNode.getProjectdesc());
        statusSpr.setSelection((Integer.valueOf(projectNode.getProjectstatus()) - 1));
        dateTv.setText(getString(R.string.feng_due_date) + "  " + projectNode.getEndstart());
        editFloatBtn.setTag(FLAG_EDIT_MODE);

        //disable edit mode
        enableEdit(false);

        //flow layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(getContext()).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }

        //fireBase database
        projectEditPresenter.initFireDb(projectNode);

        //date picker
        Calendar newCalendar = Calendar.getInstance();
        toDatePickerDialog = new DatePickerDialog(getContext(), this, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        editFloatBtn.setOnClickListener(this);
        dateTv.setOnClickListener(this);
        addMemberBtn.setOnClickListener(this);
        memberLl.setOnClickListener(this);
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), R.string.feng_date_alert, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateDate(String endDate) {
        //update new due date
        projectNode.setEndstart(endDate);

        dateTv.setText(getString(R.string.feng_due_date) + " " + endDate);
    }

    @Override
    public void changeEditMode(Boolean flag_edit_mode) {
        FLAG_EDIT_MODE = flag_edit_mode;
        enableEdit(flag_edit_mode);

        if (flag_edit_mode) {
            addMemberBtn.setVisibility(View.VISIBLE);
            editFloatBtn.setImageResource(R.drawable.ic_correct);
        } else {
            addMemberBtn.setVisibility(View.GONE);
            editFloatBtn.setImageResource(R.drawable.ic_edit);
        }
    }

    @Override
    public void showDatePicker() {
        toDatePickerDialog.show();
    }

    @Override
    public void updateMembList(List<Employee.EmployeesBean> list) {
        memberList = list;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        projectEditPresenter.datePickerClick(year, month, dayOfMonth, projectNode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_detail_project:
                projectEditPresenter.editFloatBtnClick(v, FLAG_EDIT_MODE, nameEdt, statusSpr, descEdt, projectNode);

                break;

            case R.id.tv_detail_date:
                projectEditPresenter.datePickerClick();

                break;

            case R.id.imgbtn_add_member:
                TeamDialog dialog = TeamDialog.newInstance(memberList);
                dialog.showDialog(getActivity().getSupportFragmentManager(), "employeeDlg");
                //dialog click listener
                dialog.setListener(this);

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

    @Override
    public void onComplete(List<Employee.EmployeesBean> employeeIdList) {
        //fireBase db add team member
        projectEditPresenter.updateMember(employeeIdList, projectNode);
    }
}
