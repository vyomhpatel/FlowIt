package b12app.vyom.com.flowit.networkutils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())         //gson
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //Rxjava
                    .baseUrl(BASE_URL)
                    .build();
        }


        return retrofit;
    }


}
