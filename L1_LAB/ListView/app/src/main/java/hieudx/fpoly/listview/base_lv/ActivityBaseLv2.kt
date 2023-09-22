package hieudx.fpoly.listview.base_lv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import hieudx.fpoly.listview.databinding.ActivityBaseLv1Binding
import hieudx.fpoly.listview.databinding.ActivityBaseLv2Binding

class ActivityBaseLv2 : AppCompatActivity() {
    private lateinit var binding: ActivityBaseLv2Binding
    private var list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseLv2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.lvNumber.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        binding.btnAdd.setOnClickListener {
            list.add(binding.edNumber.text.toString())
            binding.edNumber.setText("")
            binding.lvNumber.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        }

    }
}