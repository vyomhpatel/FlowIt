package b12app.vyom.com.flowit.fcmutils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.home.HomeActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class FlowItMessagingService extends FirebaseMessagingService {

    public static String TAG = "message received";
    Context context;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, Global.FROM + remoteMessage.getFrom());
        Log.d(TAG, Global.FROM + remoteMessage.getNotification().getTitle());
        Log.d(TAG, Global.FROM + remoteMessage.getSentTime());
        Log.d(TAG, Global.MESSAGE_DATA_PAYLOAD + remoteMessage.getData());

        showNotification(remoteMessage.getNotification());

    }

    public void showNotification(RemoteMessage.Notification notification) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_inbox)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setFullScreenIntent(pendingIntent, true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}