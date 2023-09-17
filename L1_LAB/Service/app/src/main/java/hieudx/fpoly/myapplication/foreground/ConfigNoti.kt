package hieudx.fpoly.myapplication.foreground

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build

class ConfigNoti : Application() {
    val CHANNEL_ID = "mp3_channel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mô tả của noti channel
            val descriptionText = "mp3 Channel Description"
//            đky noti channel
            val channel = NotificationChannel(
                CHANNEL_ID,
                "mp3 Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = descriptionText
//            đky channe với hệ thống
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }
}
