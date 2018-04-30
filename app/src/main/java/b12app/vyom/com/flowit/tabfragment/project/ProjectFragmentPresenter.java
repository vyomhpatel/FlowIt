package b12app.vyom.com.flowit.tabfragment.project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.FragmentProject;
import b12app.vyom.com.flowit.tabfragment.FragmentProjectEdit;
import b12app.vyom.com.utils.ActivityUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName ProjectFragmentPresenter
 * @Date 4/26/18, 1:04 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class ProjectFragmentPresenter implements ProjectFragmentContract.IPresenter {
    private ProjectFragmentContract.IView fragmentView;
    private DataManager mDataManager;
    private Disposable disposable;

    public ProjectFragmentPresenter(DataManager dataManager, ProjectFragmentContract.IView fragmentProject) {
        //we link model(data source) and presenter here
//        mDataManager = dataManager;

        //we link presenter and view here
        fragmentView = fragmentProject;

        fragmentView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public Disposable getProjectList(ApiService apiService) {

        apiService.getProjectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Project>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Project project) {
                        fragmentView.initRecyclerView(project);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
