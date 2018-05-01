package b12app.vyom.com.flowit.networkutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))      //gson
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //Rxjava
                    .baseUrl(BASE_URL)
                    .build();
        }


        return retrofit;
    }

    public static ApiService apiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
