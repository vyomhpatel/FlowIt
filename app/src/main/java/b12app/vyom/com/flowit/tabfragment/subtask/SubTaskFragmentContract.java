package b12app.vyom.com.flowit.tabfragment.subtask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.List;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralSubTask;

public interface SubTaskFragmentContract {

    interface IView extends BaseView<IPresenter> {

        void initRecyclerView(List<GeneralSubTask.ProjectsubtaskBean> subtasklist);

        void hideMainFloatBtn();
    }

    interface IPresenter extends BasePresenter {

        void getSubtaskList(FragmentActivity activity, Bundle arguments);

        void rvItemClick(View v, int position, List<GeneralSubTask.ProjectsubtaskBean> subtasks, FragmentActivity activity);
    }
}