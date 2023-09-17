package hieudx.fpoly.myapplication.background

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hieudx.fpoly.myapplication.R

class BackGroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_ground)

//        sau khi cho ứng dụng chạy background thì nhạc dừng sau 1 phút
        val btnPlay: Button = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener {
            startService(Intent(this, BackGrService::class.java))
        }

    }
}