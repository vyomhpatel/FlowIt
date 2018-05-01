package b12app.vyom.com.flowit.daggerUtils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.LocalDataSource;
import b12app.vyom.com.flowit.datasource.RemoteDataSource;
import dagger.Module;
import dagger.Provides;


/**
 * @Package b12app.vyom.com.flowit.daggerUtils
 * @FileName MvpModule
 * @Date 4/30/18, 10:52 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

@Module
public class MvpModule {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    DataManager providesDataManager() {
        return DataManager.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance());
    }

}
