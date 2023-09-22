package hieudxph21411.fpoly.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("onCreate", "onCreate: ")
    }

    override fun onStart() {
        super.onStart()

        Log.d("onStart", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy: ")
    }
}