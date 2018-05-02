package b12app.vyom.com.utils;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
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
    private static String taskName;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.flow_iapp_widget);


        myRef = FbHelper.getInstance().getReference(Global.TABLE_INBOX);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                inboxModelList = FbHelper.getInstance().getUserInbox(dataSnapshot, "34");
                taskName = inboxModelList.get(0).getTaskName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        views.setTextViewText(R.id.appwidget_text, taskName);


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

