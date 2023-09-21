package hieudx.fpoly.listview.advanced_lv

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import hieudx.fpoly.listview.R

class AdapteModel(private val context: Activity, private var list: ArrayList<Model>) :
    ArrayAdapter<Model>(
        context,
        R.layout.layout_item
    ) {
    override fun getCount(): Int {
//        trả về số dòng của list
        return list.size
    }

    override fun getItem(position: Int): Model? {
//        trả về obj dựa vào vị trí của đối tượng đó trong danh sách
        return super.getItem(position)
    }

    override fun getItemId(position: Int): Long {
//        trả về id trên view của từng item mà adapter tạo ra
        return super.getItemId(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater =
            context.layoutInflater //chuyển file xml thành view để hiển thị cho mỗi mục trong danh sách
        val row = inflater.inflate(R.layout.layout_item, parent, false)

        val img = row.findViewById<ImageView>(R.id.img)
        val tvTitle = row.findViewById<TextView>(R.id.tvTitle)
        val tvDes = row.findViewById<TextView>(R.id.tvDes)

        tvTitle.text = list[position].title
        tvDes.text = list[position].des
        img.setImageResource(list[position].img)
        return row
    }
}