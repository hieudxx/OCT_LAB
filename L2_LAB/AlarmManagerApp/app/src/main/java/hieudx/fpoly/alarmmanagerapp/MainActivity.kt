package hieudx.fpoly.alarmmanagerapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import hieudx.fpoly.alarmmanagerapp.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timePicker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createNotificationChannel()
        binding.btnSelectTime.setOnClickListener {
            selectTime()
        }

        binding.btnSetAlarm.setOnClickListener {
            selectAlarm()
        }

        binding.btnCancelAlarm.setOnClickListener {
            cancelAlarm()
        }
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private fun cancelAlarm() {
        val i = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0)
        if (alarmManager == null){
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        }
        alarmManager.cancel(pendingIntent)
        Toast.makeText(this, "Hủy báo thức", Toast.LENGTH_SHORT).show()

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun selectAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(this, "Cài đặt báo thức thành công", Toast.LENGTH_SHORT).show()
    }

    private fun selectTime() {
        timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(12)
            .setMinute(0).setTitleText("Chọn giờ").build()
        timePicker.show(supportFragmentManager, "alarmmanager")

        timePicker.addOnPositiveButtonClickListener {
            if (timePicker.hour > 12) {
//                binding.tvTime.text = String.format("%02d",(timePicker.hour-12)+" : "+ String.format("%02d",(timePicker.minute)+" PM"))
                binding.tvTime.text =
                    String.format("%02d : %02d PM", timePicker.hour - 12, timePicker.minute)
            } else {
                binding.tvTime.text =
                    String.format("${timePicker.hour} : ${timePicker.minute}AM")
            }

            calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

        }
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Alarm Channel"
            val descriptionText = "Alarm Channel Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
//                                                    IMPORTANCE_MAX
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audio =
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
            val channel = NotificationChannel("alarmmanager", name, importance)
//                .apply {
//                    description = descriptionText
////                    set âm thanh
//                     setSound(uri, audio)
//                }
            channel.description = descriptionText
            channel.setSound(uri, audio)
//            đky channe với hệ thống
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
