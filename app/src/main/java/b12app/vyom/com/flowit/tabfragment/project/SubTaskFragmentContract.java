package b12app.vyom.com.flowit.tabfragment.project;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import io.reactivex.disposables.Disposable;

/**
 * Created by jinliyu on 4/30/18.
 */

public interface SubTaskFragmentContract {

    interface IView extends BaseView<IPresenter> {

        void initRecyclerView(GeneralSubTask.ProjectsubtaskBean subtask);

        void hideMainFloatBtn();
    }

    interface IPresenter extends BasePresenter{
        Disposable getSubtaskList(ApiService apiService);
        void rvItemClick(View v, int position, GeneralSubTask subtask, FragmentActivity activity);
    }
}
