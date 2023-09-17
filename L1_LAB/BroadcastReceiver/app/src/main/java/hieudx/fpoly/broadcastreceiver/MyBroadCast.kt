package hieudx.fpoly.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.widget.Toast

class MyBroadCast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1?.action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            // Lấy trạng thái WiFi
            val wifiState = p1?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)

            // Kiểm tra trạng thái và thông báo
            if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
                Toast.makeText(p0, "WiFi đã bật", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(p0, "WiFi đã tắt", Toast.LENGTH_SHORT).show()
            }
        }

//            sau khi lắng nghe sẽ thông báo lại vào đây
//        if (p1?.action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
//            if (!isConnect(p0)) {
//                Toast.makeText(p0, "Bật mạng", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(p0, "Tắt mạng", Toast.LENGTH_SHORT).show()
//
//            }
//        }
    }

//    private fun isConnect(context: Context?): Boolean {
//        val cnnMana: ConnectivityManager =
//            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                ?: return false
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            từ android 6 trở xuống check mạng 1 kiểu, android 6 trở lên check mạng 1 kiểu
//            val netWork: Network = cnnMana.activeNetwork ?: return false
//            val netWorkCap: NetworkCapabilities? = cnnMana.getNetworkCapabilities(netWork)
//            return netWorkCap != null && netWorkCap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
//        } else {
//            val info: NetworkInfo? = cnnMana.activeNetworkInfo
//            return (info != null && info.isConnected)
//        }
//    }
}



