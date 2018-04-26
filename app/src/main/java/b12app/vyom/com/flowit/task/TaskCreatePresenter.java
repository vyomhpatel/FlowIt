package b12app.vyom.com.flowit.task;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;

public class TaskCreatePresenter implements TaskContract.IPresenter {

    private TaskContract.IView taskCreateView;
    private DataManager mdataManager;
    private ApiService apiService;

    public TaskCreatePresenter(DataManager dataManager, TaskCreateActivity taskCreateActivity) {
        mdataManager = dataManager;
        taskCreateView = taskCreateActivity;
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void start() {

    }


}
