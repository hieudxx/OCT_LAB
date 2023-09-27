package hieudx.fpoly.mp3application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build

class NotiConfig : Application() {
    val CHANNEL_ID = "mp3_channel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mô tả của noti channel
            val descriptionText = "mp3 Channel Description"

//            sử dụng ringtonemanager để lấy uri âm thanh của noti theo máy
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//            tạo thêm 1 audioAttributes
            val audio =
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
//            đky noti channel
            val channel = NotificationChannel(
                CHANNEL_ID,
                "mp3 Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ) // channe id, tên của channel đky, độ ưu tiên
//                .apply {
//                    description = descriptionText
////                    set âm thanh
//                     setSound(uri, audio)
//                }
            channel.description = descriptionText
            channel.setSound(uri, audio)
//            đky channe với hệ thống
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }
}