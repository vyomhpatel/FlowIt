package b12app.vyom.com.flowit.tabfragment.projectedit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.utils.FbHelper;


public class ProjectEditPresenter implements ProjectEditContract.IPresenter {
    private ProjectEditContract.IView fragmentView;
    private DataManager mDataManager;
    private DatabaseReference myRef;
    private static final String TAG = "ProjectEditPresenter";

    public ProjectEditPresenter(DataManager dataManager, ProjectEditContract.IView projectEditFgt) {

        //we link model(data source) and presenter here
        mDataManager = dataManager;
        //we link presenter and view here
        fragmentView = projectEditFgt;
    }

    @Override
    public void start() {

    }

    @Override
    public void getData(Bundle bundle) {
        Project.ProjectsBean projectNode = bundle.getParcelable(Global.PROJECT_NODE);

        fragmentView.initView(projectNode);
    }

    @Override
    public void initFireDb(final Project.ProjectsBean projectsBean) {
        myRef = FbHelper.getInstance().getReference(Global.TABLE_PROJECT_TEAM);

        mDataManager.getMemberList(myRef, new IDataSource.DbCallback() {
            @Override
            public void onSuccess(Object response) {
                List<Employee.EmployeesBean> list = (List<Employee.EmployeesBean>) response;

                fragmentView.updateMembList(list);
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, msg);
            }
        }, projectsBean);

    }

    @Override
    public void datePickerClick() {
        fragmentView.showDatePicker();
    }

    @Override
    public void datePickerClick(int year, int month, int dayOfMonth, Project.ProjectsBean projectNode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        StringBuilder sb = new StringBuilder();
        try {
            Date startDate = sdf.parse(projectNode.getStartdate());
            Date endDate = sdf.parse(sb.append(year).append("-").append(month + 1).append("-").append(dayOfMonth).toString());

            //check if end date is early than start date
            if (startDate.getTime() > endDate.getTime()) {

                fragmentView.showToast();

            } else {
                //save due date
                fragmentView.updateDate(sdf.format(endDate));

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editFloatBtnClick(View v, boolean FLAG_EDIT_MODE, TextView nameEdt, Spinner statusSpr, TextView descEdt, Project.ProjectsBean projectsBean) {
        if (!Boolean.valueOf(v.getTag().toString())) {
            //enable edit mode
            FLAG_EDIT_MODE = true;
            v.setTag(FLAG_EDIT_MODE);

            fragmentView.changeEditMode(FLAG_EDIT_MODE);

        } else {
            //disable edit mode
            FLAG_EDIT_MODE = false;
            v.setTag(FLAG_EDIT_MODE);
            fragmentView.changeEditMode(FLAG_EDIT_MODE);

            //save new name, status, desc
            String projectName = nameEdt.getText().toString();
            int projectStatus = statusSpr.getSelectedItemPosition() + 1;
            String projectDesc = descEdt.getText().toString();

            mDataManager.updateProject(projectsBean.getId(), projectName, String.valueOf(projectStatus), projectDesc, projectsBean.getStartdate(), projectsBean.getEndstart(),
                    new IDataSource.NetworkCallback() {
                        @Override
                        public void onSuccess(Object response) {

                            fragmentView.showToast(response.toString());

                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            Log.i(TAG, throwable.getLocalizedMessage());
                        }
                    });
        }
    }


    @Override
    public void updateMember(List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode) {
        mDataManager.updateMember(myRef, memberList, projectNode, new IDataSource.DbCallback() {
            @Override
            public void onSuccess(Object response) {
                fragmentView.showToast("Team created successfully");
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, msg);
            }
        });

    }

}