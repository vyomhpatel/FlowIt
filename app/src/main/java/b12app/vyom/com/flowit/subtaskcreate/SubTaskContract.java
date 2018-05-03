package b12app.vyom.com.flowit.subtaskcreate;

import android.view.View;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralSubTask;

/**
 * Created by jinliyu on 4/26/18.
 */

public interface SubTaskContract {


    interface IView extends BaseView<SubTaskContract.IPresenter> {
        void displaySnackbar(String s);


    }

    interface IPresenter extends BasePresenter {

        void onSubTaskCreateButtonClick(View view, GeneralSubTask.ProjectsubtaskBean projectsubtaskBean);


    }
}
