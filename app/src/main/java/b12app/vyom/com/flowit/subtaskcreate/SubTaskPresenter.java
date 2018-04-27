package b12app.vyom.com.flowit.subtaskcreate;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.task.TaskContract;
import b12app.vyom.com.flowit.task.TaskCreateActivity;

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
}
