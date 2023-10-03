package hieudx.fpoly.workremindmanager

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import hieudx.fpoly.workmanager.adapter.WorkAdapter
import hieudx.fpoly.workmanager.model.Work
import hieudx.fpoly.workremindmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WorkAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val pm = packageManager
        val installed = pm.getLaunchIntentForPackage("hieudx.fpoly.workmanager") != null

        if(installed) {
            loadData()

            // Gọi getLaunchIntentForPackage
        } else {
            Toast.makeText(this, "Ứng dụng nguồn chưa được cài đặt sẵn!", Toast.LENGTH_SHORT).show()
        }
//        var intent : Intent? = packageManager.getLaunchIntentForPackage("hieudx.fpoly.workmanager")
//        if (intent != null) {
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            intent.putExtra("isDone", 1)
//            startActivity(intent)
//            loadData()
//        } else {
//            Toast.makeText(this, "Ứng dụng nguồn chưa được cài đặt sẵn!", Toast.LENGTH_SHORT).show()
//        }

//        val permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
//        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(permission), 999)
//        } else {
//            // Tiếp tục thực hiện truy vấn Content Provider
//        }
//        loadData()
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
                Log.e("workList", "${workList.size}")
            }
        }

        adapter = WorkAdapter(workList)
        binding.rcv.adapter = adapter

//        dao = WorkDao(this)
//        list = dao.getAllTasks()
//        adapter = WorkAdapter(list, this)
//        binding.rcv.adapter = adapter
    }

}