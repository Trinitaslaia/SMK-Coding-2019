package com.trinitas.smkcoding.database

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {
    val Tag = "NOTIFICATION"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "Message data payload: " + remoteMessage?.getFrom());

        if (remoteMessage?.getData()?.size!!> 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() !=null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification)
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, "Refreshed token: " + p0)
    }
}