package b12app.vyom.com.flowit.tabfragment;

import b12app.vyom.com.flowit.projectcreate.ProjectContract;

/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName ProjectFragmentPresenter
 * @Date 4/26/18, 1:04 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class ProjectFragmentPresenter implements ProjectFragmentContract.IPresenter {
    ProjectFragmentContract.IView fragmentView;


    public ProjectFragmentPresenter(FragmentProject fragmentProject) {
        //we link presenter and view here
        fragmentView = fragmentProject;
        //we link model(data source) and presenter here
    }

    @Override
    public void start() {

    }
}
