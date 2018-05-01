package b12app.vyom.com.flowit.tabfragment.project;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.tabfragment.FragmentProjectEdit;
import b12app.vyom.com.utils.ActivityUtil;
import io.reactivex.disposables.Disposable;

/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName ProjectFgtPresenter
 * @Date 4/26/18, 1:04 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class ProjectFgtPresenter implements ProjectFgtContract.IPresenter {
    private ProjectFgtContract.IView fragmentView;
    private DataManager mDataManager;
    private Disposable disposable;

    private static final String TAG = "ProjectFgtPresenter";

    public ProjectFgtPresenter(DataManager dataManager, ProjectFgtContract.IView fragmentProject) {

        //we link model(data source) and presenter here
        mDataManager = dataManager;
        //we link presenter and view here
        fragmentView = fragmentProject;
        //link IView to IPresenter
        fragmentView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public Disposable getProjectList() {
        Log.i(TAG, "getProjectList: ");

        disposable = mDataManager.queryProjectList(new IDataSource.NetworkCallback() {
            @Override
            public void onSuccess(Object response) {
                Project project = (Project)response;

                fragmentView.initRecyclerView(project);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.i(TAG, e.getLocalizedMessage());
            }
        });


        return disposable;
    }

    @Override
    public void rvItemClick(View v, int position, Project project, FragmentActivity activity) {
        FragmentProjectEdit fragmentProjectEdit = new FragmentProjectEdit();
        Bundle bundle = new Bundle();
        //project info
        bundle.putParcelable("projectnode", project.getProjects().get(position));

        fragmentProjectEdit.setArguments(bundle);
        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, activity.getSupportFragmentManager(), fragmentProjectEdit, " editFgt");

        fragmentView.hideMainFloatBtn();
    }
}
