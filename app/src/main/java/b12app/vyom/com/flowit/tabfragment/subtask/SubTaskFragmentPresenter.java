package b12app.vyom.com.flowit.tabfragment.subtask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.model.UserAssignment;
import b12app.vyom.com.flowit.tabfragment.FragmentSubTaskEdit;
import b12app.vyom.com.utils.ActivityUtil;
import b12app.vyom.com.utils.SpHelper;


/**
 * Created by jinliyu on 4/30/18.
 */


/**
 * Created by jinliyu on 4/30/18.
 */

public class SubTaskFragmentPresenter implements SubTaskFragmentContract.IPresenter {

    private SubTaskFragmentContract.IView fragmentView;
    private DataManager mDataManager;
    private DatabaseReference databaseReference;

    private static final String TAG = "SubTaskFragmentPresente";

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
    public void getSubtaskList(FragmentActivity activity, User user) {
        databaseReference = FirebaseDatabase.getInstance().getReference(Global.TABLE_SUBTASK_TEAM);

        String userId = user.getUserid();

        if (!userId.equals(Global.FLAG_MANAGER)){
            mDataManager.querySubTaskListByName(databaseReference, userId, new IDataSource.DbCallback() {
                @Override
                public void onSuccess(Object response) {
                    final List<UserAssignment.UserAssignmentBean> list = (List<UserAssignment.UserAssignmentBean>)response;
                    final List<GeneralSubTask.ProjectsubtaskBean> resultList = new ArrayList<>();
                    Log.i(TAG, "onSuccess: " + list.size());

                    if (list == null || list.size() < 1){

                        fragmentView.initRecyclerView(new ArrayList<GeneralSubTask.ProjectsubtaskBean>());

                    }else {

                        for (UserAssignment.UserAssignmentBean assignmentBean : list){
                            resultList.add(new GeneralSubTask.ProjectsubtaskBean(assignmentBean.getSubtaskid(),
                                    assignmentBean.getTaskid(),
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
            mDataManager.querySubTaskList(activity, new IDataSource.NetworkCallback() {
                @Override
                public void onSuccess(Object response) {
                    List<GeneralSubTask.ProjectsubtaskBean> subtaskBeanList = (List<GeneralSubTask.ProjectsubtaskBean>) response;

                    fragmentView.initRecyclerView(subtaskBeanList);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Log.i("subtaskFrgPresenter", throwable.getLocalizedMessage());
                }
            });
        }
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