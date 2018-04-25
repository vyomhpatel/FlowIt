package b12app.vyom.com.flowit.networkutils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    //http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?mobile=55565454&password=7011

  public   static final String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();



        }
        return retrofit;
    }
}
