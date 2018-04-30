package b12app.vyom.com.flowit.projectcreate;

import android.view.View;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.Project;

public interface ProjectCreateContract {

    interface IView extends BaseView<IPresenter> {

        void displaySnackbar();

    }

    interface IPresenter extends BasePresenter {

        void onProjectCreateButtonClick(View view, Project.ProjectsBean projectsBean);

    }

}
