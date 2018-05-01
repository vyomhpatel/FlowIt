package b12app.vyom.com.flowit.tabfragment.taskedit;

import android.view.View;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralTask;

public class TaskEditContract {
    interface IView extends BaseView<TaskEditContract.IPresenter> {

            void displaySuccessSnack(String taskname);

    }

  public   interface IPresenter extends BasePresenter {

        void updateTask(View v, GeneralTask.ProjecttaskBean projecttaskBean);


    }
}
