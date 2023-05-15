package com.example.hw13_weatherapp.view

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.hw13_weatherapp.R
import com.example.hw13_weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        createNotificationChannel()

        binding.buttonNotification.setOnClickListener {
            val notification = NotificationCompat.Builder(this, "NOTI_CHANNEL_ID")
                .setSmallIcon(R.drawable.baseline_message_24)
                .setContentTitle("UYGULAMAYI ACINIZ")
                .setContentText("Uygulamayi acmak icin tiklayiniz !!!")
                .setContentIntent(getPendingIntent())
                .setPriority(NotificationCompat.COLOR_DEFAULT)
                .build()

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return@setOnClickListener
            }

            NotificationManagerCompat.from(this).notify(1, notification)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "Notification Title"
            val descricpitonText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("NOTI_CHANNEL_ID", name, importance).apply {
                description = descricpitonText
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(
            this,
            MainActivity::class.java
        )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}
