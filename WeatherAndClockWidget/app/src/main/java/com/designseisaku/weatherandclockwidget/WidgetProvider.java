package com.designseisaku.weatherandclockwidget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Calendar;

public class WidgetProvider extends AppWidgetProvider {
    Calendar calendar = Calendar.getInstance();

    String[] monthSet ={"JANUARY", "FEBRUARY"};
    String[] weekSet = {"","SunDay","MonDay","TuesDay","WednesDay","ThursDay","FriDay","SaturDay"};

    String year = Integer.toString(calendar.get(Calendar.YEAR));
    final int month = calendar.get(Calendar.MONTH);
    String date = Integer.toString(calendar.get(Calendar.DATE));
    final int week = calendar.get(Calendar.DAY_OF_WEEK);

    String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
    String mint = Integer.toString(calendar.get(Calendar.MINUTE));

    String yearT = year;
    String dateT = monthSet[month] + "." + date;
    String weekT = weekSet[week];
    String hourT = hour;
    String mintT = mint;
    String clock = hourT + ":" + mintT;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);

        views.setTextViewText(R.id.yearText, yearT);
        views.setTextViewText(R.id.dateText, dateT);
        views.setTextViewText(R.id.weekText, weekT);
        views.setTextViewText(R.id.timeText, clock);

        appWidgetManager.updateAppWidget(appWidgetIds, views);

    }
}