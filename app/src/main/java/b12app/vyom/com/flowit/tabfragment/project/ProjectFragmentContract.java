package b12app.vyom.com.flowit.tabfragment.project;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.Project;
import io.reactivex.disposables.Disposable;

/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName ProjectFragmentContract
 * @Date 4/26/18, 1:02 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public interface ProjectFragmentContract {

    interface IView extends BaseView<ProjectFragmentContract.IPresenter> {


        void initRecyclerView(Project project);

        void hideMainFloatBtn();
    }

    interface IPresenter extends BasePresenter {


        Disposable getProjectList();

        void rvItemClick(View v, int position, Project project, FragmentActivity activity);
    }
}
