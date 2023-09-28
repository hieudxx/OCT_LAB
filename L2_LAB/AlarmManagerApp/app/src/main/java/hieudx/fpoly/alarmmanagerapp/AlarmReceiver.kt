package hieudx.fpoly.alarmmanagerapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(p0: Context?, p1: Intent?) {

        val i = Intent(p0, MainActivity2::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(p0, 0, i, 0)

        val noti = NotificationCompat.Builder(p0!!, "alarmmanager")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Báo thức")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notiMana: NotificationManagerCompat = NotificationManagerCompat.from(p0)

        if (ActivityCompat.checkSelfPermission(
                p0,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//                nếu đã có quyền ta thực hiện post noti
//                nếu muốn mỗi lần click có 1 thông báo thì code dòng dưới
            notiMana.notify(1, noti.build())
        } else {
//                nếu k có quyền sẽ thực hiện xin quyền
            ActivityCompat.requestPermissions(
                p0 as Activity,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 999
            )
        }

    }
}