package com.janus.platoon.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationServiceStarterReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationEventReceiver.setupAlarm(context)
    }
}