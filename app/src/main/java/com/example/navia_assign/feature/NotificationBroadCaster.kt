package com.example.navia_assign.feature

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.navia_assign.R
import kotlin.random.Random

class NotificationBroadCaster : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val foodName = intent?.extras?.get("name") as String
        val channelId = "${context?.packageName}-${context?.getString(R.string.app_name)}"
        val builder = NotificationCompat.Builder(context!!, channelId)
            .setContentTitle("Meal Alert")
            .setContentText(foodName)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(Random.nextInt(100), builder.build())
    }
}