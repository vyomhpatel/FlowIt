package b12app.vyom.com.flowit.networkutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FCMRetrofitClient {

    public static String FCM_BASE_URL = "https://fcm.googleapis.com/";

    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit==null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(FCM_BASE_URL)
                    .build();
        }

        return retrofit;
    }

    public static FCMApiService fcmApiService() {
        return getRetrofit().create(FCMApiService.class);
    }
}
