package hieudx.fpoly.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hieudx.fpoly.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBai1.setOnClickListener {
            startActivity(Intent(this, ActivityBai1::class.java))
        }

        binding.btnBai2.setOnClickListener {
            startActivity(Intent(this, ActivityBai2::class.java))
        }


    }
}