package hieudx.fpoly.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import hieudx.fpoly.myapplication.background.BackGroundActivity
import hieudx.fpoly.myapplication.bound.BoundActivity
import hieudx.fpoly.myapplication.foreground.ForeGroundActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Foreground(view: View) {
        startActivity(Intent(this, ForeGroundActivity::class.java))
    }

    fun Background(view: View) {
        startActivity(Intent(this, BackGroundActivity::class.java))

    }

    fun Bound(view: View) {
        startActivity(Intent(this, BoundActivity::class.java))

    }
}