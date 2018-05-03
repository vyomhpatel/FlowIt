package b12app.vyom.com.flowit.tabfragment.task;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.model.UserAssignment;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import b12app.vyom.com.utils.ActivityUtil;

import static b12app.vyom.com.flowit.home.Global.FLAG_MANAGER;
import static b12app.vyom.com.flowit.home.Global.MANAGER;
import static b12app.vyom.com.flowit.home.Global.USER_ID;

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
    private DatabaseReference databaseReference;
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
    public void getTaskList(final FragmentActivity activity, User user) {
        databaseReference = FirebaseDatabase.getInstance().getReference(Global.TABLE_TASK_TEAM);
        String userId = user.getUserid();
        if (!userId.equals(FLAG_MANAGER)){
            mDataManager.queryTaskListByUser(databaseReference, userId, new IDataSource.DbCallback() {
                @Override
                public void onSuccess(Object response) {
                    final List<UserAssignment.UserAssignmentBean> list = (List<UserAssignment.UserAssignmentBean>)response;
                    final List<GeneralTask.ProjecttaskBean> resultList = new ArrayList<>();
                    Log.i(TAG, "onSuccess: " + list.size());

                    if (list == null || list.size() < 1){

                        fragmentView.initRecyclerView(new ArrayList<GeneralTask.ProjecttaskBean>());

                    }else {

                        for (UserAssignment.UserAssignmentBean assignmentBean : list){
                            resultList.add(new GeneralTask.ProjecttaskBean(assignmentBean.getTaskid(),
                                    assignmentBean.getProjectid(),
                                    assignmentBean.getTaskName(),
                                    assignmentBean.getTaskStatus(),
                                    assignmentBean.getTaskDesc(),
                                    assignmentBean.getStartdate(),
                                    assignmentBean.getEnddate()));
                        }

                        fragmentView.initRecyclerView(resultList);

                    }
                }

                @Override
                public void onFailure(String msg) {

                }

            });

        }else {
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
