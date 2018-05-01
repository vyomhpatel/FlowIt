package b12app.vyom.com.flowit.daggerUtils;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Package b12app.vyom.com.utils
 * @FileName AppModule
 * @Date 4/30/18, 10:49 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

@Module
public class AppModule {

    private final AppApplication mApplication;

    public AppModule(AppApplication mApp) {
        this.mApplication = mApp;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return mApplication;
    }

}
