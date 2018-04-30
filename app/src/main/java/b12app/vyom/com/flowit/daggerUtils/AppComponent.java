package b12app.vyom.com.flowit.daggerUtils;


import android.app.Activity;

import javax.inject.Singleton;

import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.tabfragment.FragmentProject;
import dagger.Component;

/**
 * @Package b12app.vyom.com.flowit.daggerUtils
 * @FileName AppComponent
 * @Date 4/30/18, 10:55 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    //Because strong reference, we need specify which activity/fragment we will inject
    void inject(HomeActivity activity);
    void inject(FragmentProject fragment);


    // void inject(MyFragment fragment);
    // void inject(MyService service);
}
