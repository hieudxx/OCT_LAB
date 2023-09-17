package hieudx.fpoly.myapplication.bound

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {
    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    fun caculator(number1: Int, number2: Int, temp: Int): Int {
        when (temp) {
            1 -> return number1 + number2
            2 -> return number1 - number2
            3 -> return number1 * number2
            4 -> return number1 / number2
        }
        return 0

    }
}