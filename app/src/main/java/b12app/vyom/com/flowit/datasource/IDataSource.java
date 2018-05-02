package b12app.vyom.com.flowit.datasource;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import io.reactivex.disposables.Disposable;

public interface IDataSource {

    void querySubTaskList(FragmentActivity activity, NetworkCallback networkCallback);

    void queryTaskList(FragmentActivity activity, NetworkCallback networkCallback);

    void updateProject(String id, String projectName, String projectStatus, String projectDesc, String startDate, String endDate, NetworkCallback networkCallbackn);

    void getMemberList(DatabaseReference myRef, DbCallback dbCallback, Project.ProjectsBean projectsBean);

    void updateMember(DatabaseReference myRef, List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode, DbCallback dbCallback);

    void updateTask(GeneralTask.ProjecttaskBean taskNode, NetworkCallback networkCallback);


    interface DbCallback {

        void onSuccess(Object response);

        void onFailure(String msg);
    }

    interface NetworkCallback {

        void onSuccess(Object response);

        void onFailure(Throwable throwable);
    }


    void updateSubTask(String userId, GeneralSubTask.ProjectsubtaskBean subtaskNode, NetworkCallback networkCallback);

    Disposable queryProjectList(NetworkCallback networkCallback);


}
