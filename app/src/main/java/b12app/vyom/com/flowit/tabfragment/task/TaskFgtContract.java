package b12app.vyom.com.flowit.tabfragment.task;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.List;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;


/**
 * @Package b12app.vyom.com.flowit.tabfragment.task
 * @FileName TaskFgtContract
 * @Date 4/30/18, 10:49 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public interface TaskFgtContract {

    interface IView extends BaseView<TaskFgtContract.IPresenter> {

        void initRecyclerView(List<GeneralTask.ProjecttaskBean> taskBeanList);

        void hideMainFloatBtn();
    }

    interface IPresenter extends BasePresenter {

        void getTaskList(FragmentActivity activity);

        void rvItemClick(View v, int position, List<GeneralTask.ProjecttaskBean> project, FragmentActivity activity);

    }
}
