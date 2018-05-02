package b12app.vyom.com.utils;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import b12app.vyom.com.flowit.R;

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
            views.setTextViewText(R.id.tvResult, "result = " + i);
            i++;
        }

        //update AppWidget
        appWidgetManager.updateAppWidget(thisWidget, views);
    }

    //
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e("widget", "onUpdate");
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
