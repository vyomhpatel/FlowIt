package b12app.vyom.com.flowit.fcmutils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FlowItInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "id service tag";

    private static FlowItInstanceIdService instance = null;

    //a private constructor so no instances can be made outside this class
    private FlowItInstanceIdService() {
    }

    private String refreshedToken;


    //Everytime you need an instance, call this
    //synchronized to make the call thread-safe
    public static synchronized FlowItInstanceIdService getInstance() {
        if (instance == null)
            instance = new FlowItInstanceIdService();

        return instance;
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token测试: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    public void sendRegistrationToServer(String refreshedToken) {

    }


}