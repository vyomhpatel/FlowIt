package b12app.vyom.com.flowit.tabfragment.task;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import b12app.vyom.com.utils.ActivityUtil;

/**
 * @Package b12app.vyom.com.flowit.tabfragment.task
 * @FileName TaskFgtPresenter
 * @Date 4/30/18, 10:49 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class TaskFgtPresenter implements TaskFgtContract.IPresenter {
    private TaskFgtContract.IView fragmentView;
    private DataManager mDataManager;
    private static final String TAG = "TaskFgtPresenter";

    public TaskFgtPresenter(DataManager dataManager, TaskFgtContract.IView fragmentTask) {

        //we link model(data source) and presenter here
        mDataManager = dataManager;
        //we link presenter and view here
        fragmentView = fragmentTask;
        //link IView to IPresenter
        fragmentView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getTaskList(FragmentActivity activity) {
        mDataManager.queryTaskList(activity, new IDataSource.NetworkCallback() {
            @Override
            public void onSuccess(Object response) {
                List<GeneralTask.ProjecttaskBean> taskBeanList = (List<GeneralTask.ProjecttaskBean>)response;

                fragmentView.initRecyclerView(taskBeanList);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i(TAG, throwable.getLocalizedMessage());
            }
        });
    }

    @Override
    public void rvItemClick(View v, int position, List<GeneralTask.ProjecttaskBean> taskBeanList, FragmentActivity activity) {
        FragmentTaskEdit fragmentTaskEdit = new FragmentTaskEdit();
        Bundle bundle = new Bundle();
        bundle.putParcelable("taskNode", taskBeanList.get(position));
        fragmentTaskEdit.setArguments(bundle);
        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, activity.getSupportFragmentManager(), fragmentTaskEdit, "task");

        fragmentView.hideMainFloatBtn();
    }
}
