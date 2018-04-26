package b12app.vyom.com.flowit.task;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;

public interface TaskContract {

    interface IView extends BaseView<IPresenter>{
        void displaySnackbar();


    }

    interface IPresenter extends BasePresenter {



    }

}
