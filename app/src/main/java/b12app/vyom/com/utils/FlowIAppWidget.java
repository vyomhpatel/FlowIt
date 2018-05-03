package b12app.vyom.com.utils;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.InboxModel;

/**
 * Implementation of App Widget functionality.
 */
public class FlowIAppWidget extends AppWidgetProvider {

    private static DatabaseReference myRef;
    private static List<InboxModel> inboxModelList;
    private ComponentName thisWidget;
    private RemoteViews views;
    private static String taskName, taskDesc;
    private static String TAG="tag widget";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                 int appWidgetId) {


        views = new RemoteViews(context.getPackageName(), R.layout.flow_iapp_widget);

        views.setTextViewText(R.id.tv_widget_title, taskName);
        views.setTextViewText(R.id.tv_widget_description,taskDesc);


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (final int appWidgetId : appWidgetIds) {

            myRef = FbHelper.getInstance().getReference(Global.TABLE_INBOX);
            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    inboxModelList = FbHelper.getInstance().getUserInbox(dataSnapshot, "34");
                    if(inboxModelList.size() > 0){
                        taskName = inboxModelList.get(0).getTaskName();
                        taskDesc = inboxModelList.get(0).getTaskDesc();
                        Log.i(TAG, "onDataChange: " + inboxModelList.get(0).getTaskName());

                    }else{
                        taskName = "Test Task";
                        taskDesc = "Test Task Description";

                    }

                    updateAppWidget(context, appWidgetManager, appWidgetId);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

