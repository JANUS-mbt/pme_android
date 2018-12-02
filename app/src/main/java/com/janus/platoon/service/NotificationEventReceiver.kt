package com.janus.platoon.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver
import android.util.Log
import java.util.*

class NotificationEventReceiver : WakefulBroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        var serviceIntent: Intent? = null
        if (ACTION_START_NOTIFICATION_SERVICE == action) {
            Log.i(javaClass.simpleName, "onReceive from alarm, starting notification service")
            serviceIntent = NotificationIntentService.createIntentStartNotificationService(context)
        } else if (ACTION_DELETE_NOTIFICATION == action) {
            Log.i(
                javaClass.simpleName,
                "onReceive delete notification action, starting notification service to handle delete"
            )
            serviceIntent = NotificationIntentService.createIntentDeleteNotification(context)
        }

        if (serviceIntent != null) {
            WakefulBroadcastReceiver.startWakefulService(context, serviceIntent)
        }
    }

    companion object {

        private val ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE"
        private val ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION"

        fun setupAlarm(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = getStartPendingIntent(context)
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                getTriggerAt(Date()),
                alarmIntent
            )
        }

        private fun getTriggerAt(now: Date): Long {
            val calendar = Calendar.getInstance()
            calendar.time = now
            return calendar.timeInMillis + 60 * 100
        }

        private fun getStartPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, NotificationEventReceiver::class.java)
            intent.action = ACTION_START_NOTIFICATION_SERVICE
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        fun getDeleteIntent(context: Context): PendingIntent {
            val intent = Intent(context, NotificationEventReceiver::class.java)
            intent.action = ACTION_DELETE_NOTIFICATION
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}