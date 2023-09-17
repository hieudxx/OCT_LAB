package hieudx.fpoly.myapplication.foreground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val action = p1?.getIntExtra("action", 0)
//        chuyển action này ngược lại service
        val i = Intent(p0, ForeGrSerivce::class.java)
        i.putExtra("action_service", action)
        p0?.startService(i)
    }
}