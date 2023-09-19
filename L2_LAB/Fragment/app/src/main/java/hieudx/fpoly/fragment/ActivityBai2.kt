package hieudx.fpoly.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ActivityBai2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai2)

        val frag1 = Fragment1()
        val frag2 = Fragment2()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container1, frag1)
            add(R.id.container2, frag2)
            commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onStart: ")
    }
}