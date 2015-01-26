package com.designseisaku.weatherandclockwidget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Calendar;

public class WidgetProvider extends AppWidgetProvider {
    Calendar calendar = Calendar.getInstance();

    String year = Integer.toString(calendar.get(Calendar.YEAR));

    String date = year+".";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);

        views.setTextViewText(R.id.dayText, date);
    }
}