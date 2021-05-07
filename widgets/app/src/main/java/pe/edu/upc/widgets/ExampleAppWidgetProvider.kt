package pe.edu.upc.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class ExampleAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->

            val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
                .let { intent ->
                    PendingIntent.getActivity(context, 0, intent, 0)

                }

            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.example_widget
            )
                .apply {
                    setOnClickPendingIntent(R.id.button, pendingIntent)
                }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}