package hieudx.fpoly.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import hieudx.fpoly.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var countViewModel: CountViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayCount()

        countViewModel =
            ViewModelProvider(this).get(CountViewModel::class.java) //liên kết UI với viewmodel
        binding.btnUp.setOnClickListener {
            countViewModel?.count = countViewModel?.count!! + 1
            displayCount()
        }

        binding.btnDown.setOnClickListener {
            countViewModel?.count = countViewModel?.count!! - 1
            displayCount()
        }
    }

    private fun displayCount() {
        binding.tvCount.text = countViewModel?.count.toString()
    }
}