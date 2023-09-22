package hieudx.fpoly.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hieudx.fpoly.listview.advanced_lv.ActivityAdvLv
import hieudx.fpoly.listview.base_lv.ActivityBaseLv1
import hieudx.fpoly.listview.base_lv.ActivityBaseLv2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBaseLv1: Button = findViewById(R.id.btnBaseLv1)
        btnBaseLv1.setOnClickListener {
            startActivity(Intent(this, ActivityBaseLv1::class.java))
        }
        val btnBaseLv2: Button = findViewById(R.id.btnBaseLv2)
        btnBaseLv2.setOnClickListener {
            startActivity(Intent(this, ActivityBaseLv2::class.java))
        }

        val btnAdvLv: Button = findViewById(R.id.btnAdvLv)
        btnAdvLv.setOnClickListener {
            startActivity(Intent(this, ActivityAdvLv::class.java))

        }
    }
}