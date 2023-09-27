package hieudx.fpoly.myapplication

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val NOTIFICATION_ID =
        1 // ở đây đang set mặc định còn nếu id khác nhau thì khi ấn sẽ gửi đc nhiều noti
    private val CHANNEL_ID = "my_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        val btnSend: Button = findViewById(R.id.btnSend)
        val btnCustom: Button = findViewById(R.id.btnCustom)

        btnCustom.setOnClickListener {
            sendCustomNoti()
        }

        btnSend.setOnClickListener {
            sendNoti()
        }
    }

    private fun sendCustomNoti() {
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.music)
        val notiLayout = RemoteViews(packageName, R.layout.layout_custom_noti)
        notiLayout.setTextViewText(R.id.tvCustomTitle, "title custom noti")
        notiLayout.setTextViewText(R.id.tvCustomInfo, "message custom noti")

        val sdf = SimpleDateFormat("HH:mm:ss")
        var date = sdf.format(Date())
        notiLayout.setTextViewText(R.id.tvCustomTime, date)
        
        val noti = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.img) //small icon
            .setSound(uri)
            .setCustomContentView(notiLayout)

        val notiMana: NotificationManagerCompat = NotificationManagerCompat.from(this)

//            code ktra quyền noti trên device
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//                nếu đã có quyền ta thực hiện post noti
//                nếu muốn mỗi lần click có 1 thông báo thì code dòng dưới
            notiMana.notify(Date().time.toInt(), noti.build())
        } else {
//                nếu k có quyền sẽ thực hiện xin quyền
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 999
            )
        }
    }

    private fun sendNoti() {
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img)

        val noti = NotificationCompat.Builder(this, CHANNEL_ID)
//            val noti = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("title post noti")
            .setContentText("message post noti")
            .setSmallIcon(R.drawable.img) //small icon
            .setLargeIcon(bitmap)
            .setColor(resources.getColor(R.color.black))
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null)
            )
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("nếu muốn nội dung dài nhiều dòng thì dùng cái nàyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")
            )
            .setAutoCancel(true)

        val notiMana: NotificationManagerCompat = NotificationManagerCompat.from(this)

//            code ktra quyền noti trên device
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//                nếu đã có quyền ta thực hiện post noti
//                nếu muốn mỗi lần click có 1 thông báo thì code dòng dưới
            notiMana.notify(Date().time.toInt(), noti.build())
        } else {
//                nếu k có quyền sẽ thực hiện xin quyền
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 999
            )
        }
//            val notiMana: NotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notiMana.notify(NOTIFICATION_ID, noti)
    }

    @SuppressLint("WrongConstant")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            tên của noti channel cần đky
            val name = "My Channel"
//            mô tả của noti channel
            val descriptionText = "My Channel Description"
//            độ ưu tiên của noti
            val importance = NotificationManager.IMPORTANCE_HIGH
//                                                    IMPORTANCE_MAX
//            sử dụng ringtonemanager để lấy uri âm thanh của noti theo máy
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//            tạo thêm 1 audioAttributes
            val audio =
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
//            đky noti channel
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
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

    //    tạo hàm xin quyền nếu ứng dụng chưa cấp quyền
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 7799) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    sendNoti()
                }
            }
        }
    }
}