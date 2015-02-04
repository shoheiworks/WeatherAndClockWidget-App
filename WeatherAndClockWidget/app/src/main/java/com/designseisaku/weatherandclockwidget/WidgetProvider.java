package com.designseisaku.weatherandclockwidget;


import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.format.Time;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;


import java.net.ContentHandler;
import java.util.Calendar;


public class WidgetProvider extends AppWidgetProvider {

    Calendar calendar = Calendar.getInstance();

    String[] monthSet = {"JANUARY", "FEBRUARY"};
    String[] weekSet = {"", "SunDay", "MonDay", "TuesDay", "WednesDay", "ThursDay", "FriDay", "SaturDay"};

    String year = Integer.toString(calendar.get(Calendar.YEAR));
    final int month = calendar.get(Calendar.MONTH);
    String date = Integer.toString(calendar.get(Calendar.DATE));
    final int week = calendar.get(Calendar.DAY_OF_WEEK);
//    String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
//    String mint = Integer.toString(calendar.get(Calendar.MINUTE));

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);

        testM(context,appWidgetManager,appWidgetIds);

        views.setTextViewText(R.id.yearText, year);
        views.setTextViewText(R.id.dateText, monthSet[month] + "." + date);
        views.setTextViewText(R.id.weekText, weekSet[week]);

//        SharedPreferences tempPre = getSharedPreferences();

//        Intent intent = getIntent();
//        Intent intent = new Intent(context, MainActivity.class);
//        String telopInt = intent.getStringExtra("temp");

//        views.setTextViewText(R.id.telop_temperature, aaa);
        appWidgetManager.updateAppWidget(appWidgetIds, views);

    };


    private Runnable upDate;
    private Handler mHandler = new Handler();

    public void testM(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        upDate = new Runnable() {
            public void run() {
                Time time = new Time("Asia/Tokyo");
                time.setToNow();
                String timeM = Integer.toString(time.minute);
                String timeH = Integer.toString(time.hour);
                String timeT = timeH + "時" + timeM + "分";

                RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widgetlayout);
                views.setTextViewText(R.id.timeText,timeT);
                appWidgetManager.updateAppWidget(appWidgetIds, views);
                mHandler.removeCallbacks(upDate);
                mHandler.postDelayed(upDate, 1000);
            }
        };
        mHandler.postDelayed(upDate,1000);
    }
    class SetTime extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            
        }
    }


    /*
    @Override
    public void onReceive(Context context, Intent intent) {

    }


    public static class WidgetService extends Service {
        @Override
        public void onStart(Intent intent,int si){
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_TIME_TICK);
            registerReceiver(timeReceiver, filter);


        }
        @Override
        public IBinder onBind(Intent intent){
            return null;
        }
    }
*/
    public static BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        int minits = 110;
        @Override
        public void onReceive(Context context, Intent intent) {
            String ac = intent.getAction();
            if (ac.equals(Intent.ACTION_TIME_TICK)){
                minits = intent.getIntExtra("minits",0);
            }

        }
    };

}