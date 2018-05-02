package b12app.vyom.com.flowit.datasource;

import android.support.v4.app.FragmentActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import io.reactivex.disposables.Disposable;

public class DataManager implements IDataSource {
    private IDataSource remoteDataSource;
    private IDataSource localDataSource;
    private static DataManager INSTANCE = null;

    private DataManager(IDataSource remoteDataSource, IDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static DataManager getInstance(IDataSource remoteDataSource, IDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataManager(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void queryTaskList(FragmentActivity activity, NetworkCallback networkCallback) {

        remoteDataSource.queryTaskList(activity, networkCallback);
    }

    @Override
    public void updateProject(String id, String projectName, String projectStatus, String projectDesc, String startDate, String endDate, NetworkCallback networkCallback) {
        remoteDataSource.updateProject(id, projectName, projectStatus, projectDesc, startDate, endDate,networkCallback);
    }

    @Override
    public void getMemberList(DatabaseReference myRef, DbCallback dbCallback, Project.ProjectsBean projectsBean) {
        localDataSource.getMemberList(myRef, dbCallback, projectsBean);
    }

    @Override
    public void updateMember(DatabaseReference myRef, List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode, DbCallback dbCallback) {
        localDataSource.updateMember(myRef, memberList, projectNode, dbCallback);
    }

    @Override
    public void updateTask(GeneralTask.ProjecttaskBean taskNode, NetworkCallback networkCallback) {
        remoteDataSource.updateTask(taskNode, networkCallback);
    }

    @Override
    public void getTaskMemberList(DatabaseReference myRef, DbCallback dbCallback, GeneralTask.ProjecttaskBean taskNode) {
        localDataSource.getTaskMemberList(myRef, dbCallback, taskNode);
    }

    @Override
    public void queryTaskListByUser(DatabaseReference databaseReference, String userId, DbCallback dbCallback) {
        localDataSource.queryTaskListByUser(databaseReference, userId, dbCallback);
    }

    @Override
    public Disposable queryProjectList(final NetworkCallback networkCallback) {

        return remoteDataSource.queryProjectList(networkCallback);
    }


}
