package hieudx.fpoly.workmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hieudx.fpoly.workmanager.WorkDao
import hieudx.fpoly.workmanager.databinding.ActivityAddWorkBinding
import hieudx.fpoly.workmanager.databinding.ActivityListWorkBinding
import hieudx.fpoly.workmanager.model.Work
import java.util.Calendar

class ActivityAddWork : AppCompatActivity() {
    private lateinit var binding: ActivityAddWorkBinding
    private lateinit var dao: WorkDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWorkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dao = WorkDao(this)
        selectTime()
        binding.btnAdd.setOnClickListener {
            addWork()
        }

    }

    private fun selectTime() {
        val today = Calendar.getInstance()
        binding.datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val msg = "Ngày bạn chọn: $day/$month/$year"
            binding.tvTime.text = msg
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addWork() {

        val name = binding.edName.text.toString()
        val time = binding.tvTime.text.toString()

        if (name.isNotEmpty() && time.isNotEmpty()) {
            val check = dao.insert(name, time)
            if (check) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()

            }
        } else {
            Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show()
        }
    }
}