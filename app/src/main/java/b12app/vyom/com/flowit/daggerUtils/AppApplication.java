package b12app.vyom.com.flowit.daggerUtils;

import android.app.Application;
import android.content.Context;

/**
 * @Package b12app.vyom.com.flowit.daggerUtils
 * @FileName AppApplication
 * @Date 4/30/18, 10:56 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class AppApplication extends Application {
    private AppComponent appComponent;
    private static final String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";

    public static  AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                //list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) //
                .netModule(new NetModule(BASE_URL))//
                .build();

        //if a dagger2 components does not have any constructor arguments for any of its modules
        //then we can use .create() as a shortcut instead
        //mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();

    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
