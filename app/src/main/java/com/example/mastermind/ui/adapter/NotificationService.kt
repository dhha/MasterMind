package com.example.mastermind.ui.adapter

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import com.example.mastermind.MainActivity
import com.example.mastermind.R
import com.example.mastermind.ui.model.MasterMindDatabase
import com.example.mastermind.ui.model.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NotificationService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Add code to send notifications here
        CoroutineScope(Dispatchers.Default + Job()).launch {
            val schedules = MasterMindDatabase(applicationContext).getScheduleDao().getAllSchedule()
            for (sche in schedules) {
                sendNotification(sche)
            }
        }
        return START_STICKY
    }

    @SuppressLint("MissingPermission")
    private fun sendNotification(notification: Schedule) {
        // Create an intent to launch when the notification is clicked
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        // Create a notification builder
        val builder = NotificationCompat.Builder(this, "my_channel_id")
            .setSmallIcon(R.drawable.icon_play)
            .setContentTitle(notification.course)
            .setContentText(notification.name + " will take place on " +notification.date + " " +notification.time+ " at " + notification.location)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        // Send the notification
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notification.id, builder.build())
    }
}