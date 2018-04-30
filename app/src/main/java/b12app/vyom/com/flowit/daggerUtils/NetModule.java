package b12app.vyom.com.flowit.daggerUtils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import b12app.vyom.com.flowit.networkutils.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Package b12app.vyom.com.flowit.daggerUtils
 * @FileName NetModule
 * @Date 4/30/18, 10:52 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

@Module
public class NetModule {
    private String BASE_URL;

    public NetModule(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    protected ApiService provideApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava2
                .addConverterFactory(GsonConverterFactory.create()) // Gson
                .build();
        return retrofit.create(ApiService.class);
    }

}
