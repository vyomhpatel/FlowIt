package b12app.vyom.com.flowit.daggerUtils;


import javax.inject.Singleton;

import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.login.LoginActivity;
import b12app.vyom.com.flowit.projectcreate.ProjectCreateActivity;
import b12app.vyom.com.flowit.subtaskcreate.SubTaskCreateActivity;
import b12app.vyom.com.flowit.tabfragment.FragmentInbox;
import b12app.vyom.com.flowit.tabfragment.FragmentProjectEdit;
import b12app.vyom.com.flowit.tabfragment.FragmentSubTask;
import b12app.vyom.com.flowit.tabfragment.FragmentSubTaskEdit;
import b12app.vyom.com.flowit.tabfragment.FragmentTask;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import b12app.vyom.com.flowit.task.TaskCreateActivity;
import dagger.Component;

/**
 * @Package b12app.vyom.com.flowit.daggerUtils
 * @FileName AppComponent
 * @Date 4/30/18, 10:55 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

@Singleton
@Component(modules = {AppModule.class, MvpModule.class})
public interface AppComponent {

    //Because strong reference, we need specify which activity/fragment we will inject
    void inject(HomeActivity activity);

    void inject(FragmentProjectEdit fragmentProjectEdit);

    void inject(FragmentTaskEdit fragmentTaskEdit);

    void inject(ProjectCreateActivity projectCreateActivity);

    void inject(TaskCreateActivity taskCreateActivity);

    void inject(FragmentSubTaskEdit fragmentSubTaskEdit);

    void inject(SubTaskCreateActivity subTaskCreateActivity);

    void inject(LoginActivity loginActivity);

    void inject(FragmentInbox fragmentInbox);

    void inject(FragmentTask fragmentTask);

    void inject(FragmentSubTask fragmentSubTask);


}
