package hieudx.fpoly.myapplication.bound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import hieudx.fpoly.myapplication.R

class BoundActivity : AppCompatActivity() {
    private var isServiceConnected = false
    private lateinit var boundService: BoundService
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            giao tiếp với service ở trong đây
//            nếu kết nối thành công
            val binder = service as BoundService.MyBinder
            boundService = binder.getService()
            isServiceConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceConnected = false
        }
    }

    var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound)

        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        val edNum1: EditText = findViewById(R.id.edNum1)
        val edNum2: EditText = findViewById(R.id.edNum2)
        tvResult = findViewById(R.id.tvResult)

        val btnPlus: Button = findViewById(R.id.btnPlus)
        btnPlus.setOnClickListener {
            if (isServiceConnected) {
                var number1 = edNum1.text.toString()
                var number2 = edNum2.text.toString()

                handleOnclick(number1, number2, 1)

            }
        }

        val btnSub: Button = findViewById(R.id.btnSub)
        btnSub.setOnClickListener {
            if (isServiceConnected) {
                var number1 = edNum1.text.toString()
                var number2 = edNum2.text.toString()

                handleOnclick(number1, number2, 2)

            }
        }

        val btnMul: Button = findViewById(R.id.btnMul)
        btnMul.setOnClickListener {
            if (isServiceConnected) {
                var number1 = edNum1.text.toString()
                var number2 = edNum2.text.toString()

                handleOnclick(number1, number2, 3)
            }
        }

        val btnDiv: Button = findViewById(R.id.btnDiv)
        btnDiv.setOnClickListener {
            if (isServiceConnected) {
                var number1 = edNum1.text.toString()
                var number2 = edNum2.text.toString()

                handleOnclick(number1, number2, 4)

            }
        }
    }

    private fun handleOnclick(number1: String, number2: String, temp: Int) {
        if (number1.isEmpty() || number2.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số vào cả hai trường", Toast.LENGTH_SHORT)
                .show()
            return
        }
        try {
            val so1 = number1.toInt()
            val so2 = number2.toInt()

            if (isServiceConnected) {
                val result = boundService.caculator(so1, so2, temp)
                tvResult?.text = "Kết quả: $result"
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Vui lòng chỉ nhập số", Toast.LENGTH_SHORT).show()
            Log.e("NumberFormatException", "Lỗi chuyển đổi số nguyên: ${e.message}")
        }

    }
}