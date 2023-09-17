package hieudx.fpoly.myapplication.foreground

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hieudx.fpoly.myapplication.R

class ForeGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fore_ground)

        val btnStart: Button = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {

            clickStartService()
        }

    }

    private fun clickStartService() {
        var song = Song("Chơi như tụi mỹ", "Andree", R.drawable.music, R.raw.music)
        val i = Intent(this, ForeGrSerivce::class.java)
        val bundle = Bundle()
        bundle.putSerializable("song", song)
        i.putExtras(bundle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(i)
        }
    }

}