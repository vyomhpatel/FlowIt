package b12app.vyom.com.flowit.tabfragment.taskedit;

import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskEditPresenter implements TaskEditContract.IPresenter {
    public static String TAG = "task edit presenter";
    private TaskEditContract.IView iView;
    private FragmentTaskEdit fragmentTaskEdit;
    private DataManager dataManager;

    public TaskEditPresenter(FragmentTaskEdit fragmentTaskEdit, DataManager dataManager) {
        this.fragmentTaskEdit = fragmentTaskEdit;
        this.dataManager = dataManager;
//        iView = fragmentTaskEdit;
    }

    @Override
    public void start() {

    }

    @Override
    public void updateTask(View v, final GeneralTask.ProjecttaskBean projecttaskBean) {

//        String user_id = "14";
//        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
//        apiService.updateTaskStatus(projecttaskBean.getTaskid(),projecttaskBean.getProjectid(),user_id,projecttaskBean.getTaskstatus())
//                .enqueue(new Callback<JSONObject>() {
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//
//                try {
//                    Log.i(TAG, "task edit status: "+response.body().getJSONArray("msg").getString(0));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                iView.displaySuccessSnack(projecttaskBean.getTaskname());
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//                Log.i(TAG, "task edit failed: "+t.getMessage());
//            }
//        });
    }
}
