package b12app.vyom.com.flowit.datasource;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import io.reactivex.disposables.Disposable;

public interface IDataSource {


    void queryTaskList(FragmentActivity activity, NetworkCallback networkCallback);

    void updateProject(String id, String projectName, String projectStatus, String projectDesc, String startDate, String endDate, NetworkCallback networkCallbackn);

    void getMemberList(DatabaseReference myRef, DbCallback dbCallback, Project.ProjectsBean projectsBean);

    void updateMember(DatabaseReference myRef, List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode, DbCallback dbCallback);

    void updateTask(GeneralTask.ProjecttaskBean taskNode, NetworkCallback networkCallback);

    void getTaskMemberList(DatabaseReference myRef, DbCallback dbCallback, GeneralTask.ProjecttaskBean taskNode);

    interface DbCallback {

        void onSuccess(Object response);

        void onFailure(String msg);
    }

    interface NetworkCallback {

        void onSuccess(Object response);

        void onFailure(Throwable throwable);
    }


    Disposable queryProjectList(NetworkCallback networkCallback);


}
