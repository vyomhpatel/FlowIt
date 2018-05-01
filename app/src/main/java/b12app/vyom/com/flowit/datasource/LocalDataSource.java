package b12app.vyom.com.flowit.datasource;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.utils.FbHelper;
import io.reactivex.disposables.Disposable;

public class LocalDataSource implements IDataSource {

    private static LocalDataSource instance;

    private LocalDataSource() {

    }

    public static LocalDataSource getInstance() {
        if (instance == null) {
            synchronized (LocalDataSource.class) {
                if (instance == null) {
                    instance = new LocalDataSource();
                }
            }
        }
        return instance;
    }


    @Override
    public void queryTaskList(FragmentActivity activity, NetworkCallback networkCallback) {

    }

    @Override
    public void updateProject(String id, String projectName, String projectStatus, String projectDesc, String startDate, String endDate, NetworkCallback networkCallbackn) {

    }

    @Override
    public void getMemberList(DatabaseReference myRef, final DbCallback dbCallback, final Project.ProjectsBean projectsBean) {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dbCallback.onSuccess(FbHelper.getInstance().getProjectTeam(dataSnapshot, projectsBean.getId()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                dbCallback.onFailure(error.getMessage());
            }
        });
    }

    @Override
    public void updateMember(DatabaseReference myRef, List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode, DbCallback dbCallback) {
        FbHelper.getInstance().addTeamMember(myRef, memberList, projectNode.getId());
        dbCallback.onSuccess("");
    }

    @Override
    public void updateTask(GeneralTask.ProjecttaskBean taskNode, NetworkCallback networkCallback) {

    }

    @Override
    public void getTaskMemberList(DatabaseReference myRef, final DbCallback dbCallback, final GeneralTask.ProjecttaskBean taskNode) {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dbCallback.onSuccess(FbHelper.getInstance().getTaskTeam(dataSnapshot, taskNode.getProjectid(), taskNode.getTaskid()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                dbCallback.onFailure(error.getMessage());
            }
        });
    }

    @Override
    public Disposable queryProjectList(NetworkCallback networkCallback) {
        return null;
    }
}
