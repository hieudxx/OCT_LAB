package hieudx.fpoly.workmanager.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hieudx.fpoly.workmanager.WorkDao
import hieudx.fpoly.workmanager.databinding.ItemWorkBinding
import hieudx.fpoly.workmanager.model.Work

class WorkAdapter(private var list: ArrayList<Work>, private val context: Context) :
    RecyclerView.Adapter<WorkAdapter.ViewHolder>() {
    val dao = WorkDao(context)

    inner class ViewHolder(val itemBinding: ItemWorkBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWorkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.apply {
            tvName.text = "Tên công việc: ${list[position].name}"
            tvTime.text = "Thời gian: ${list[position].time}"
            imgDelete.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Thông báo")
                builder.setMessage("Bạn có chắc chắn muốn xóa ?")
                builder.setNegativeButton("Có") { _: DialogInterface?, _: Int ->
                    dao.deleteWork(list[position].id)
                    list.clear()
                    list = dao.getAllTasks()
                    notifyDataSetChanged()
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show()
                }
                builder.setPositiveButton("Không", null)
                builder.show()
            }
        }
    }


}