package b12app.vyom.com.flowit.projectcreate;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;

public class ProjectCreatePresenter implements ProjectContract.IPresenter {
    private ProjectContract.IView projectCreateView;
    private IDataSource dataManager;

    //ProjectContract.IView can be fragment/activity
    public ProjectCreatePresenter(DataManager dataManager, ProjectContract.IView fragment) {
        dataManager = dataManager;
        projectCreateView = fragment;
    }

    @Override
    public void start() {

    }



}
