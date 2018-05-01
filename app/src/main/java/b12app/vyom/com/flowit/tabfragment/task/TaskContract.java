package b12app.vyom.com.flowit.tabfragment.task;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralTask;

public interface TaskContract {

    interface IView extends BaseView{

    }

    interface IPresenter extends BasePresenter{

        void getEmplList();
        void initRecyclerView(GeneralTask.ProjecttaskBean generalTask);
    }

}
