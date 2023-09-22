package hieudx.fpoly.listview.base_lv

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import hieudx.fpoly.listview.R
import hieudx.fpoly.listview.databinding.ActivityBaseLv1Binding

class ActivityBaseLv1 : AppCompatActivity() {
    private lateinit var binding: ActivityBaseLv1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseLv1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        xây dựng những giá trị cố định cho lv, trường hợp thực tế như các ngày trong tuần, tháng trong năm...
//        là những trường hợp mà lv k hề thêm hay bớt giá trị
//        những giá trị đó sẽ khai báo trong values > strings.xml
        val arr = resources.getStringArray(R.array.arrDays)
        binding.lvBase.setBackgroundColor(Color.parseColor("#99FFFF"))
        binding.lvBase.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,// vẽ layout cho từng dòng 1, ở đây ta đang sử dụng layout sẵn có của android
            arr// dữ liệu truyền vào
        )

        binding.lvBase.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Bạn đang chọn ${arr[i]}", Toast.LENGTH_SHORT).show()
        }
    }
}

