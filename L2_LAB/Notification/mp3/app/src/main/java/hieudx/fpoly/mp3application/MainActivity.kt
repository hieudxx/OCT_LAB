package hieudx.fpoly.mp3application

import android.annotation.SuppressLint
import android.app.Notification
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaSession2Service.MediaNotification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

import androidx.core.app.NotificationCompat.EXTRA_MEDIA_SESSION
import androidx.core.app.NotificationManagerCompat
import java.util.Date

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay: Button = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener {
            sendNotiMp3()
        }
    }

    private fun sendNotiMp3() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img)
        val mediaSession = MediaSessionCompat(this, "tag")
        val noti = NotificationCompat.Builder(this, NotiConfig().CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_music_note_24)
            .setSubText("Bộ sưu tập")
            .setContentTitle("Tên bài hát")
            .setContentText("Tên ca sĩ")
            .setLargeIcon(bitmap)
            .addAction(R.drawable.baseline_skip_previous_24, "Previous", null)
            .addAction(R.drawable.baseline_pause_24, "Pause", null)
            .addAction(R.drawable.baseline_skip_next_24, "Next", null)
            .addAction(R.drawable.baseline_close_24, "Next", null)
            .setStyle(

                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0,1,2,3 /* #1: pause button \*/)
                    .setMediaSession(mediaSession.getSessionToken())
            )
        val notiMana: NotificationManagerCompat = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//                nếu đã có quyền ta thực hiện post noti
            notiMana.notify(1, noti.build())
        } else {
//                nếu k có quyền sẽ thực hiện xin quyền
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 999
            )
        }


    }
}