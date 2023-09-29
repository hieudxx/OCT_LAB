package hieudx.fpoly.workmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hieudx.fpoly.workmanager.databinding.ItemWorkBinding
import hieudx.fpoly.workmanager.model.Work

class WorkAdapter(private val listWork: ArrayList<Work>) :
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
        return listWork.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.apply {
            tvName.text = "Tên công việc: ${listWork[position].name}"
            tvTime.text = "Thời gian: ${listWork[position].time}"
        }


    }


}