package b12app.vyom.com.flowit.tabfragment.taskedit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import b12app.vyom.com.utils.FbHelper;

public class TaskEditPresenter implements TaskEditContract.IPresenter {
    public static String TAG = "task edit presenter";
    private TaskEditContract.IView fragmentView;
    private DataManager mDataManager;
    private DatabaseReference myRef;
    private Context context;

    public TaskEditPresenter(DataManager dataManager, FragmentTaskEdit taskEdtFgt) {
        this.mDataManager = dataManager;
        fragmentView = taskEdtFgt;
        context = taskEdtFgt.getActivity();
    }

    @Override
    public void start() {

    }

    @Override
    public void getData(Bundle arguments) {
        fragmentView.initView(arguments.getParcelable("taskNode"));
    }

    @Override
    public void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, GeneralTask.ProjecttaskBean taskNode) {

        if (!Boolean.valueOf(v.getTag().toString())) {
            //start edit mode
            flagEditMode = true;
            v.setTag(flagEditMode);
            fragmentView.changeEditMode(flagEditMode);

        } else {
            flagEditMode = false;
            v.setTag(flagEditMode);
            fragmentView.changeEditMode(flagEditMode);

        }
    }

    @Override
    public void updateTask(GeneralTask.ProjecttaskBean taskNode) {
        mDataManager.updateTask(taskNode, new IDataSource.NetworkCallback() {
            @Override
            public void onSuccess(Object response) {
                List<String> msgRes = (List<String>)response;

                fragmentView.showToast(msgRes.get(0));
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
                Log.i(TAG, "updateTask: " + msgRes.get(0));


            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i(TAG, throwable.getLocalizedMessage());
            }
        });
    }

    @Override
    public void initFireDb(GeneralTask.ProjecttaskBean taskNode) {
        myRef = FbHelper.getInstance().getReference(Global.TABLE_TASK_TEAM);

        mDataManager.getTaskMemberList(myRef, new IDataSource.DbCallback() {
            @Override
            public void onSuccess(Object response) {
                List<Employee.EmployeesBean> list = (List<Employee.EmployeesBean>) response;

                fragmentView.updateMembList(list);
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, msg);
            }
        }, taskNode);
    }

    @Override
    public void addTaskMember(int position, List<Employee.EmployeesBean> list, GeneralTask.ProjecttaskBean taskNode) {
        FbHelper.getInstance().addTaskTeamMember(myRef, list.get(position), taskNode.getProjectid(), taskNode);
    }

}