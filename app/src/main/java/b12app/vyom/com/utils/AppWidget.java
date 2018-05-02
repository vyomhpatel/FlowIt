package b12app.vyom.com.utils;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.daggerUtils.AppComponent;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.InboxModel;

/**
 * @Package b12app.vyom.com.utils
 * @FileName AppWidget
 * @Date 5/2/18, 4:00 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class AppWidget extends AppWidgetProvider {

    private static int i = 1;
    private static final String ACTION = "click";
    private DatabaseReference myRef;
    Context context;
    private List<InboxModel> inboxModelList;

    //when we create an app widget object
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }



    //on receive
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("widget", "onReceive + " + intent.getAction());
        super.onReceive(context, intent);

        ComponentName thisWidget = new ComponentName(context, AppWidget.class);//define container
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);//define remote view
        //define AppWidgetManager
        AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(context);

        //receive Intent to refresh widget UI
        if (intent.getAction().equals(ACTION)) {
            views.setTextViewText(R.id.tvResult, "result = " + inboxModelList.get(0).getTaskName());

        }

        //update AppWidget
        appWidgetManager.updateAppWidget(thisWidget, views);
    }

    //
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e("widget", "onUpdate");
        myRef = FbHelper.getInstance().getReference(Global.TABLE_INBOX);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                inboxModelList =  FbHelper.getInstance().getUserInbox(dataSnapshot, "34");
                Log.i("appTest", "onDataChange: " + inboxModelList.size() + "");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        for (int id : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            Intent intent1 = new Intent("click");//define intent
            PendingIntent pendingIntentOne = PendingIntent.getBroadcast(context, 0, intent1, 0);
            views.setOnClickPendingIntent(R.id.btnAdd, pendingIntentOne);//click listener
            //update AppWidget
            appWidgetManager.updateAppWidget(id, views);
        }


    }

    //when widget delete
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }


}
