package b12app.vyom.com.flowit.tabfragment.subtask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.tabfragment.FragmentSubTaskEdit;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import b12app.vyom.com.flowit.tabfragment.subtask.SubTaskFragmentContract;
import b12app.vyom.com.utils.ActivityUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jinliyu on 4/30/18.
 */

public class SubTaskFragmentPresenter implements SubTaskFragmentContract.IPresenter {

    private SubTaskFragmentContract.IView fragmentView;
    private DataManager mDataManager;



    public SubTaskFragmentPresenter( DataManager mDataManager, SubTaskFragmentContract.IView fragmentSubtask) {
        //we link model(data source) and presenter here
        this.mDataManager = mDataManager;
        //we link presenter and view here
        fragmentView = fragmentSubtask;
        //link IView to IPresenter
        fragmentView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getSubtaskList(FragmentActivity activity) {
        mDataManager.querySubTaskList(activity, new IDataSource.NetworkCallback() {
            @Override
            public void onSuccess(Object response) {
                List<GeneralSubTask.ProjectsubtaskBean> subtaskBeanList = (List<GeneralSubTask.ProjectsubtaskBean>)response;

                fragmentView.initRecyclerView(subtaskBeanList);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i("subtaskFrgPresenter", throwable.getLocalizedMessage());
            }
        });

    }



    @Override
    public void rvItemClick(View v, int position, List<GeneralSubTask.ProjectsubtaskBean> subtaskList, FragmentActivity activity) {
        Log.i("test","clicked!!!!!");
        FragmentSubTaskEdit fragmentsubTaskEdit = new FragmentSubTaskEdit();
        Bundle bundle = new Bundle();
        bundle.putParcelable("subtaskNode", subtaskList.get(position));
        fragmentsubTaskEdit.setArguments(bundle);
        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, activity.getSupportFragmentManager(), fragmentsubTaskEdit, "task");

        fragmentView.hideMainFloatBtn();
    }
}
