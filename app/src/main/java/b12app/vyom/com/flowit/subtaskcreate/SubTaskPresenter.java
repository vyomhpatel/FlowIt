package b12app.vyom.com.flowit.subtaskcreate;

import android.util.Log;
import android.view.View;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.subtask.SubTaskFragmentContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinliyu on 4/26/18.
 */

public class SubTaskPresenter implements SubTaskContract.IPresenter {

    private SubTaskContract.IView subtaskCreateView;
    private DataManager mdataManager;
    private ApiService apiService;

    public SubTaskPresenter(DataManager dataManager, SubTaskCreateActivity subtaskCreateActivity) {
        mdataManager = dataManager;
        subtaskCreateView = subtaskCreateActivity;
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void start() {

    }



    @Override
    public void onSubTaskCreateButtonClick(View view, GeneralSubTask.ProjectsubtaskBean projectsubtaskBean) {
        Call<GeneralSubTask> call = apiService.postSubTask(
                projectsubtaskBean.getProjectid(),
                projectsubtaskBean.getTaskid(),
                projectsubtaskBean.getSubtaskname(),
                projectsubtaskBean.getSubtaskstatus(),
                projectsubtaskBean.getSubtaskdesc(),
                projectsubtaskBean.getStartdate(),
                projectsubtaskBean.getEndstart());

        call.enqueue(new Callback<GeneralSubTask>() {
            @Override
            public void onResponse(Call<GeneralSubTask> call, Response<GeneralSubTask> response) {
                Log.i("test", "on create task response: "+response);
//                subtaskCreateView.displaySnackbar();
            }

            @Override
            public void onFailure(Call<GeneralSubTask> call, Throwable t) {

            }
        });
    }

}
