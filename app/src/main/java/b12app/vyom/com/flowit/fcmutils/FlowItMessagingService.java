package b12app.vyom.com.flowit.fcmutils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import b12app.vyom.com.flowit.R;

public class FlowItMessagingService extends FirebaseMessagingService {

    public static String TAG = "message received";
    Context context;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            Notification.Builder builder = new Notification.Builder(context).setContentTitle(title).setContentText(message);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0,builder.build());


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


}
