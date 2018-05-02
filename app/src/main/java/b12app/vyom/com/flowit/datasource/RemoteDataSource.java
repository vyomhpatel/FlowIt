package b12app.vyom.com.flowit.datasource;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.MsgReponseBody;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.model.UserAssignment;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements IDataSource {

    private Disposable disposable;

    private List<GeneralTask.ProjecttaskBean> taskBeanList;
    private List<UserAssignment.UserAssignmentBean> userAssignmentList;

    private static RemoteDataSource instance;
    private static final String TAG = "RemoteDataSource";

    private RemoteDataSource() {

    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (RemoteDataSource.class) {
                if (instance == null) {
                    instance = new RemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void queryTaskList(FragmentActivity activity, final NetworkCallback networkCallback) {
        String url = "http://rjtmobile.com/aamir/pms/android-app/pms_project_task_list.php?";

        taskBeanList = new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(activity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray projecttask = response.getJSONArray("project task");
                    for (int i = 0; i < projecttask.length(); i++) {

                        JSONObject task = projecttask.getJSONObject(i);
                        String taskid = task.getString("taskid");
                        String projectid = task.getString("projectid");
                        String taskname = task.getString("taskname");
                        String taskstatus = task.getString("taskstatus");
                        String taskdesc = task.getString("taskdesc");
                        String startdate = task.getString("startdate");
                        String endstart = task.getString("endstart");
                        GeneralTask.ProjecttaskBean projecttaskBean = new GeneralTask.ProjecttaskBean(
                                taskid, projectid, taskname, taskstatus, taskdesc, startdate, endstart);
                        taskBeanList.add(projecttaskBean);

                    }

                    networkCallback.onSuccess(taskBeanList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                networkCallback.onFailure(error);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void updateProject(String id, String projectName, String projectStatus, String projectDesc, String startDate, String endDate, final NetworkCallback networkCallback) {
        //do network call to update
        RetrofitInstance.apiService().updateProject(id, projectName, projectStatus, projectDesc, startDate, endDate).enqueue(new Callback<MsgReponseBody>() {

            @Override
            public void onResponse(Call<MsgReponseBody> call, Response<MsgReponseBody> response) {

                networkCallback.onSuccess(response.body().getMsg().get(0));
            }

            @Override
            public void onFailure(Call<MsgReponseBody> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getMemberList(DatabaseReference myRef, DbCallback dbCallback, Project.ProjectsBean projectsBean) {

    }

    @Override
    public void updateMember(DatabaseReference myRef, List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode, DbCallback dbCallback) {

    }

    @Override
    public void updateTask(GeneralTask.ProjecttaskBean taskNode, final NetworkCallback networkCallback) {

        String user_id = "14";

        RetrofitInstance.apiService().updateTaskStatus(taskNode.getTaskid(), taskNode.getProjectid(), user_id, taskNode.getTaskstatus())
                .enqueue(new Callback<MsgReponseBody>() {
                    @Override
                    public void onResponse(Call<MsgReponseBody> call, Response<MsgReponseBody> response) {
                        networkCallback.onSuccess(response.body().getMsg());
                    }

                    @Override
                    public void onFailure(Call<MsgReponseBody> call, Throwable t) {
                        networkCallback.onFailure(t);
                    }
                });
    }

    @Override
    public void getTaskMemberList(DatabaseReference myRef, DbCallback dbCallback, GeneralTask.ProjecttaskBean taskNode) {

    }

    @Override
    public void queryTaskListByUser(DatabaseReference databaseReference, String userId, DbCallback dbCallback) {

    }


    @Override
    public Disposable queryProjectList(final NetworkCallback networkCallback) {

        RetrofitInstance.apiService().getProjectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Project>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Project project) {
                        networkCallback.onSuccess(project);
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkCallback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return disposable;
    }
}
