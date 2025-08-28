package com.example.siaga

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.siaga.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
class AlarmForegroundService : Service() {
    private val database = Firebase.database("https://siaga-f42dd-default-rtdb.asia-southeast1.firebasedatabase.app")
    private var listener: ValueEventListener? = null
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var notification: Notification

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("foreground-bind", "ok")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("foreground-start", "ok")
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationBuilder = NotificationCompat.Builder(this, "running_chanel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Alarm Darurat Gempa ")
            .setContentText("your alarm is running")

        notification = notificationBuilder.build()
        startForeground(1, notification)
        listenForUpdates()

        return START_REDELIVER_INTENT
    }

    private fun listenForUpdates() {
        val ref = database.getReference("alarm_on")
        listener = ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("foreground-service", snapshot.toString())

                if (snapshot.exists() && snapshot.getValue(Boolean::class.java) == true) {
                    Log.d("foreground-service", "Alarm triggered!")

                    showCustomScreen()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("foreground-service", "Error listening for updates: ${error.message}")
            }
        })
    }

    private fun showCustomScreen() {
        MainActivity.ChangeRoute(Screens.Notif)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )

        notificationBuilder.setContentTitle("Notifikasi Gempa !!!")
        notificationBuilder.setContentText("Sedang Terjadi Gempa")
        notificationBuilder.setContentIntent(pendingIntent)

        notification = notificationBuilder.build()
        notificationManager.notify(2, notification)
    }
}