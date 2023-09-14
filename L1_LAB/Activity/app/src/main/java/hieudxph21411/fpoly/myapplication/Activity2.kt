package hieudxph21411.fpoly.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)


        val bundle = intent.extras
        var id = bundle?.getInt("id")
        var name = bundle?.getString("name")

        var tvId:TextView = findViewById(R.id.tvId)
        var tvName:TextView = findViewById(R.id.tvName)

        tvId.setText(id.toString())
        tvName.setText(name)
    }
}