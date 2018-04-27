package b12app.vyom.com.flowit.task;

import android.util.Log;
import android.view.View;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskCreatePresenter implements TaskContract.IPresenter {

    private TaskContract.IView taskCreateView;
    private DataManager mdataManager;
    private ApiService apiService;
    private String TAG = TaskCreateActivity.class.getSimpleName();

    public TaskCreatePresenter(DataManager dataManager, TaskCreateActivity taskCreateActivity) {
        mdataManager = dataManager;
        taskCreateView = taskCreateActivity;
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void start() {

    }


    @Override
    public void onTaskCreateButtonClick(View view, GeneralTask.ProjecttaskBean projecttaskBean) {

        Call<GeneralTask> call = apiService.postTask(
                projecttaskBean.getProjectid(),
                projecttaskBean.getTaskname(),
                projecttaskBean.getTaskstatus(),
                projecttaskBean.getTaskdesc(),
                projecttaskBean.getStartdate(),
                projecttaskBean.getEndstart());

        call.enqueue(new Callback<GeneralTask>() {
            @Override
            public void onResponse(Call<GeneralTask> call, Response<GeneralTask> response) {
                Log.i(TAG, "on create task response: "+response);
                taskCreateView.displaySnackbar();
            }

            @Override
            public void onFailure(Call<GeneralTask> call, Throwable t) {

            }
        });

    }
}
