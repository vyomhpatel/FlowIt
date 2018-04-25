package b12app.vyom.com.flowit.networkutils;

import java.util.List;

import b12app.vyom.com.flowit.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("pms_reg.php?")
    Call<User>postUser(@Query("first_name") String first_name, @Query("last_name") String last_name,@Query("email") String email
    , @Query("mobile") String mobile, @Query("password") String password, @Query("company_size") String company_size, @Query("your_role") String your_role);

    @GET("shop_login.php")
    Call<List<User>> getUser(@Query("mobile") String mobileNumber, @Query("password") String password);




}
