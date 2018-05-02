package b12app.vyom.com.flowit.networkutils;

import b12app.vyom.com.flowit.model.FcmResponse;
import b12app.vyom.com.flowit.model.Sender;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMApiService {

    //token:cxb3ZD3T0WA:APA91bHkJ2xeWuetzmeBYWCLKifkY580vCgSXi0XhJuwPd8hdyp9KaVknUc2Hn_ovLS2q26orWpaIQnIGk3IHPyCzKF_FucfrutpEbyW1J6m42ub_DIMxa6-J0pbayBxw9BYLCGOVbdr
    //https://fcm.googleapis.com/fcm/send
    //Content-Type:application/json
    //Authorization:key=AIzaSyZ-1u...0GBYzPu7Udno5aA
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAQtuXyyU:APA91bEaNV_axpnTKeV-SID52k3IWBOXmGrau58Jqq1gtmHhVWLpLli-BwgQHNCdRWE_qMjnuxCT9wNrUb6OU1o5wwtND4w7PoTIyUb2a8iizur9hDN5PgOfnJX3IeEEq3nlZozvDVNz"
    })
    @POST("fcm/send")
    Call<FcmResponse> sendNotification(@Body Sender body);
}