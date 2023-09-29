package hieudx.fpoly.workmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hieudx.fpoly.workmanager.R
import hieudx.fpoly.workmanager.databinding.ActivityAddWorkBinding
import hieudx.fpoly.workmanager.databinding.ActivityListWorkBinding

class ActivityAddWork : AppCompatActivity() {
    private lateinit var binding: ActivityAddWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWorkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnTime.setOnClickListener {
            showDatePicker()
        }

        binding.btnAdd.setOnClickListener {
            addWork()
        }

    }

    private fun addWork() {
        TODO("Not yet implemented")
    }

    private fun showDatePicker() {
        TODO("Not yet implemented")
    }
}