package b12app.vyom.com.flowit.projectcreate;

import android.util.Log;
import android.view.View;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectCreatePresenter implements ProjectContract.IPresenter {
    private ProjectContract.IView projectCreateView;
    private IDataSource dataManager;
    private ApiService apiService;
    private static String TAG = "presnter project tag";


    //ProjectContract.IView can be fragment/activity
    public ProjectCreatePresenter(DataManager dataManager, ProjectCreateActivity fragment) {
        dataManager = dataManager;
        projectCreateView = fragment;
        apiService  = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void start() {

    }


    @Override
    public void onProjectCreateButtonClick(View view, Project.ProjectsBean projectsBean) {

        Call<Project.ProjectsBean> call=apiService.postProject(projectsBean.getProjectname(),projectsBean.getProjectstatus(),projectsBean.getProjectdesc()
        ,projectsBean.getStartdate(),projectsBean.getEndstart());

        call.enqueue(new Callback<Project.ProjectsBean>() {
            @Override
            public void onResponse(Call<Project.ProjectsBean> call, Response<Project.ProjectsBean> response) {
                Log.i(TAG, "onResponse: "+response);
                projectCreateView.displaySnackbar();
            }

            @Override
            public void onFailure(Call<Project.ProjectsBean> call, Throwable t) {
                Log.i(TAG, "onFailure: project creation"+t);
            }
        });
    }
}
