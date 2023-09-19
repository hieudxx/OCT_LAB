package hieudx.fpoly.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityBai1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai1)

        val frag1 = Fragment1()

        supportFragmentManager.beginTransaction().add(R.id.fragContainer,frag1).commit()
    }
}