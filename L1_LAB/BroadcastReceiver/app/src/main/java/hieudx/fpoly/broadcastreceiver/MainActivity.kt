package hieudx.fpoly.broadcastreceiver

import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val receiver = MyBroadCast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Đăng ký receiver trong manifest
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))

    }
    
    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}