package hieudx.fpoly.listview.advanced_lv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hieudx.fpoly.listview.R
import hieudx.fpoly.listview.databinding.ActivityActivitAdvLvBinding

class ActivityAdvLv : AppCompatActivity() {
    private lateinit var binding: ActivityActivitAdvLvBinding
    private lateinit var adapter: AdapteModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitAdvLvBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var list = arrayListOf<Model>()
        list.add(Model(R.drawable.ny1, "tiêu đề 1", "mô tả 1"))
        list.add(Model(R.drawable.ny1, "tiêu đề 1", "mô tả 1"))
        list.add(Model(R.drawable.ic_launcher_foreground, "tiêu đề 2", "mô tả 2"))
        list.add(Model(R.drawable.ic_launcher_foreground, "tiêu đề 2", "mô tả 2"))
        list.add(Model(R.drawable.ic_launcher_background, "tiêu đề 3", "mô tả 3"))
        list.add(Model(R.drawable.z4225269277977_6e806603d5e40ffbb9188ac3d75f1bd3, "tiêu đề 4", "mô tả 4"))
        list.add(Model(R.drawable.z4225269277977_6e806603d5e40ffbb9188ac3d75f1bd3, "tiêu đề 4", "mô tả 4"))
        list.add(Model(R.drawable.ny1, "tiêu đề 5", "mô tả 5"))
        list.add(Model(R.drawable.ny1, "tiêu đề 6", "mô tả 6"))
        list.add(Model(R.drawable.ny1, "tiêu đề 6", "mô tả 6"))

        binding.lvAdv.adapter = AdapteModel(this,list)
        binding.lvAdv.setOnItemClickListener { adapterView, view, i, l ->
//            adapterView: nơi nhấp chuột đã xảy ra
//            view: chế độ xem trong chế độ xem bộ điều hợp đã đc nhấp (đây sẽ là chế độ xem do bộ điều hợp cung cấp)
//            i int: vị trí của khung nhìn trong bộ điều hợp
//            l long: id hàng của mục đã nhấp
            Toast.makeText(this,"Bạn đang chọn "+list[i].title,Toast.LENGTH_SHORT).show()
        }
    }
}