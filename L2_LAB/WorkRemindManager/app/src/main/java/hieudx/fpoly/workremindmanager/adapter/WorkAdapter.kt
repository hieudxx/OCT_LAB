package hieudx.fpoly.workmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import hieudx.fpoly.workmanager.model.Work
import hieudx.fpoly.workremindmanager.databinding.ItemWorkBinding

class WorkAdapter(private var list: ArrayList<Work>) :
    RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

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
        }
    }


}