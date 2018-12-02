package com.janus.platoon.service

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.content.WakefulBroadcastReceiver
import android.util.Log
import com.janus.platoon.R
import com.janus.platoon.ui.platoon.PlatoonActivity

class NotificationIntentService : IntentService(NotificationIntentService::class.java.simpleName) {

    override fun onHandleIntent(intent: Intent?) {
        Log.d(javaClass.simpleName, "onHandleIntent, started handling a notification event")
        try {
            val action = intent!!.action
            if (ACTION_START == action) {
                processStartNotification()
            }
            if (ACTION_DELETE == action) {
                processDeleteNotification(intent)
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent!!)
        }
    }

    private fun processDeleteNotification(intent: Intent) {
        // Log something?
    }

    private fun processStartNotification() {
        // Do something. For example, fetch fresh data from backend to create a rich notification?

        val builder = NotificationCompat.Builder(this)
        builder.setContentTitle("Scheduled Notification")
            .setAutoCancel(true)
            .setColor(resources.getColor(R.color.colorAccent))
            .setContentText("This notification has been triggered by Notification Service")
            .setSmallIcon(R.drawable.ic_truck)

        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            Intent(this, PlatoonActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this))

        val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {

        private val NOTIFICATION_ID = 1
        private val ACTION_START = "ACTION_START"
        private val ACTION_DELETE = "ACTION_DELETE"

        fun createIntentStartNotificationService(context: Context): Intent {
            val intent = Intent(context, NotificationIntentService::class.java)
            intent.action = ACTION_START
            return intent
        }

        fun createIntentDeleteNotification(context: Context): Intent {
            val intent = Intent(context, NotificationIntentService::class.java)
            intent.action = ACTION_DELETE
            return intent
        }
    }
}