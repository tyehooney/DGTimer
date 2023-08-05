package com.example.dgtimer.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.example.dgtimer.R
import com.example.dgtimer.activities.main.MainActivity
import com.example.dgtimer.activities.timer.TimerActivity

class DGTimerWidgetProvider : AppWidgetProvider() {

    override fun onEnabled(context: Context?) {
        log("onEnabled()")
        super.onEnabled(context)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        context ?: return
        log("onUpdate()")
        appWidgetIds?.forEach { appWidgetId ->
            val remoteViews = createRemoteViews(context)
            appWidgetManager?.updateAppWidget(appWidgetId, remoteViews)
            appWidgetManager?.notifyAppWidgetViewDataChanged(appWidgetId, R.id.capsule_list_view)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        context ?: return
        log("onReceive() action:${intent?.action}")
        when (intent?.action) {
            ACTION_CLICK_ITEM -> {
                val itemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)
                val timerIntent = TimerActivity.createTimerActivityIntent(context, itemId).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(timerIntent)
            }
            else -> Unit
        }
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        log(
            "onRestored() oldWidgetIds:${oldWidgetIds?.contentToString()} " +
                    "newWidgetIds:${newWidgetIds?.contentToString()}"
        )
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }

    override fun onDisabled(context: Context?) {
        log("onDisabled")
        super.onDisabled(context)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        log("onDeleted() widgetIds:${appWidgetIds?.contentToString()}")
        super.onDeleted(context, appWidgetIds)
    }

    private fun createRemoteViews(context: Context): RemoteViews {
        val remoteViews =
            RemoteViews(context.packageName, R.layout.widget_starred_capsule_list)

        remoteViews.setTextViewText(R.id.tv_widget_app_name, context.getText(R.string.app_name))
        val launcherIntent = MainActivity.createMainActivityIntent(context).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val launcherPendingIntent = PendingIntent.getActivity(
            context,
            0,
            launcherIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        remoteViews.setOnClickPendingIntent(R.id.ll_widget_root, launcherPendingIntent)

        remoteViews.setRemoteAdapter(
            R.id.capsule_list_view,
            Intent(context, DGTimerWidgetRemoteViewsService::class.java)
        )
        remoteViews.setEmptyView(R.id.capsule_list_view, R.id.fl_empty_list)
        val clickItemIntent = Intent(context, DGTimerWidgetProvider::class.java).apply {
            action = ACTION_CLICK_ITEM
        }
        val clickItemPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            clickItemIntent,
            PendingIntent.FLAG_MUTABLE
        )
        remoteViews.setPendingIntentTemplate(R.id.capsule_list_view, clickItemPendingIntent)

        return remoteViews
    }

    private fun log(message: String) {
        Log.d("DGTimerWidgetProvider", message)
    }

    companion object {
        private const val ACTION_CLICK_ITEM = "action.click.item"
        const val EXTRA_ITEM_ID = "extra.item.position"

        internal fun notifyAppWidgetUpdate(
            context: Context
        ) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(
                ComponentName(
                    context,
                    DGTimerWidgetProvider::class.java
                )
            )
            appWidgetManager.notifyAppWidgetViewDataChanged(
                appWidgetIds,
                R.id.capsule_list_view
            )
        }
    }
}