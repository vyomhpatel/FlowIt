package b12app.vyom.com.flowit.projectcreate;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;


import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.FragmentProject;
import b12app.vyom.com.utils.ActivityUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectCreatePresenter implements ProjectCreateContract.IPresenter {
    private ProjectCreateContract.IView projectCreateView;
    private IDataSource dataManager;
    private ApiService apiService;
    private Context context;
    private static String TAG = "presnter project tag";


    //ProjectContract.IView can be fragment/activity
    public ProjectCreatePresenter(DataManager dataManager, ProjectCreateActivity fragment) {
        dataManager = dataManager;
        projectCreateView = fragment;
        context = fragment;
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void start() {

    }


    @Override
    public void onProjectCreateButtonClick(View view, final Project.ProjectsBean projectsBean) {

        Call<Project.ProjectsBean> call = apiService.postProject(projectsBean.getProjectname(), projectsBean.getProjectstatus(), projectsBean.getProjectdesc()
                , projectsBean.getStartdate(), projectsBean.getEndstart());

        call.enqueue(new Callback<Project.ProjectsBean>() {
            @Override
            public void onResponse(Call<Project.ProjectsBean> call, Response<Project.ProjectsBean> response) {

                Log.i(TAG, Global.ON_RESPONSE + ": " + response);


                projectCreateView.displaySnackbar();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);

                    }
                }, 500);

            }

            @Override
            public void onFailure(Call<Project.ProjectsBean> call, Throwable t) {
                Log.i(TAG, Global.ON_FAILURE_PROJECT_CREATION + t);
            }
        });
    }
}
