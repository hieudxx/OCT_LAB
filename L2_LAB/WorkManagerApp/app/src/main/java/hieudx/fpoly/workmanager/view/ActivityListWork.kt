package hieudx.fpoly.workmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hieudx.fpoly.workmanager.databinding.ActivityListWorkBinding

class ActivityListWork : AppCompatActivity() {
    private lateinit var binding: ActivityListWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListWorkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, ActivityAddWork::class.java))
        }
    }
}