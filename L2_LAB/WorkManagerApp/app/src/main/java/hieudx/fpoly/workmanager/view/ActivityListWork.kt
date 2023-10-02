package hieudx.fpoly.workmanager.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hieudx.fpoly.workmanager.WorkDao
import hieudx.fpoly.workmanager.adapter.WorkAdapter
import hieudx.fpoly.workmanager.databinding.ActivityListWorkBinding
import hieudx.fpoly.workmanager.model.Work

class ActivityListWork : AppCompatActivity() {
    private lateinit var binding: ActivityListWorkBinding
    private lateinit var list: ArrayList<Work>
    private lateinit var adapter: WorkAdapter
    private lateinit var dao: WorkDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListWorkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, ActivityAddWork::class.java))
        }

    }

    private fun loadData() {
        val uri = Uri.parse("content://hieudx.fpoly.workmanager/work")
        val projection = arrayOf("id", "name", "time")
        val cursor = contentResolver.query(uri, projection, null, null, null)

        val workList = ArrayList<Work>()

        cursor?.use { cursor ->
            val idIndex = cursor.getColumnIndex("id")
            val nameIndex = cursor.getColumnIndex("name")
            val timeIndex = cursor.getColumnIndex("time")

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                val name = cursor.getString(nameIndex)
                val time = cursor.getString(timeIndex)

                val work = Work(id.toInt(), name, time)
                workList.add(work)
            }
        }

        adapter = WorkAdapter(workList, this)
        binding.rcv.adapter = adapter

//        dao = WorkDao(this)
//        list = dao.getAllTasks()
//        adapter = WorkAdapter(list, this)
//        binding.rcv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}