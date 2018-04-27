package b12app.vyom.com.flowit.task;

import android.view.View;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;

public interface TaskContract {

    interface IView extends BaseView<IPresenter>{
        void displaySnackbar();


    }

    interface IPresenter extends BasePresenter {

        void  onTaskCreateButtonClick(View view, GeneralTask.ProjecttaskBean projecttaskBean);

    }

}
