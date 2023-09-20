package hieudx.fpoly.inten_bundle

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import hieudx.fpoly.inten_bundle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        explictit intent
        binding.btnGoWeb.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=WK_k8uKuEKA&list=PLPt6-BtUI22qf3KE1V1PyAm1v8M2qqwL5&index=79")
                )
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.SEND_SMS), 1)
            }
        }

//        nhắn tin
        binding.btnSms.setOnClickListener {
//            ACTION_SENT: dùng khi muốn xác định trạng thái gửi tin nhắn thành công hay thất bại
//            ACTION_SEND: dùng khi k quan tâm trạng thái gửi tin nhắn
            val sms = SmsManager.getDefault()

//            là 1 intent nhưng dạng chờ, chờ xong mới kích hoạt lên
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                Intent("SMS_SENT"),
                PendingIntent.FLAG_IMMUTABLE
            )

            val deliveredIntent = PendingIntent.getBroadcast(
                this,
                0,
                Intent("SMS_DELIVERED"),
                PendingIntent.FLAG_IMMUTABLE
            )

            registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    when (resultCode) {
                        Activity.RESULT_OK -> {
//                            Tin nhắn đc gửi thành công
                            Toast.makeText(p0, "Tin nhắn đc gửi thành công", Toast.LENGTH_SHORT)
                                .show()
                        }

                        SmsManager.RESULT_ERROR_GENERIC_FAILURE -> {
                            Toast.makeText(p0, "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show()
                        }

                        SmsManager.RESULT_ERROR_NO_SERVICE -> {
                            Toast.makeText(p0, "Không có sóng", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }, IntentFilter("SMS_SENT"))
            sms.sendTextMessage(
                "0973967774",
                null,
                binding.edSms.text.toString(),
                pendingIntent,
                deliveredIntent
            )
        }

//        quay số k cần cấp quyền
        binding.btnNumber.setOnClickListener {
            val uri = Uri.parse("tel:" + binding.edPhone.text.toString())
            startActivity(Intent(Intent.ACTION_DIAL, uri))
        }

//        gọi luôn
//        nhưng cần ktra xem ng dùng có cấp quyền hay k, nếu k thì phải xin cấp quyền và xử lý kết quả cấp quyền ở hàm onRequestPermissionsResult bên dưới
        binding.btnCall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    101
                )
            } else {
                val uri = Uri.parse("tel:" + binding.edPhone.text.toString())
                startActivity(Intent(Intent.ACTION_CALL, uri))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 101 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            val uri = Uri.parse("tel:" + binding.edPhone.text.toString())
            startActivity(Intent(Intent.ACTION_CALL, uri))
        } else {
            Toast.makeText(this, "Không thể gọi do bạn đã từ chối cấp quyền", Toast.LENGTH_SHORT)
                .show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}