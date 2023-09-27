package hieudx.fpoly.speedfood.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hieudx.fpoly.speedfood.databinding.ItemMainCourseRcvBinding
import hieudx.fpoly.speedfood.model.Food
import hieudx.fpoly.speedfood.viewmodel.FoodViewModel


class FoodAdapter(private val listFood: ArrayList<Food>, private val viewModel: FoodViewModel) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    private val checkedList: ArrayList<Food> = ArrayList()
    inner class ViewHolder(val itemBinding: ItemMainCourseRcvBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemMainCourseRcvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.apply {
            imgFood.setImageResource(listFood[position].img)
            tvNameFood.text = listFood[position].name
            tvDesFood.text = listFood[position].desc
            tvCount.text = listFood[position].count.toString()
            cbCheck.isChecked = listFood[position].isCheck

            tvPlus.setOnClickListener {
                listFood[position].plusCount()
                notifyDataSetChanged()
            }

            tvMinus.setOnClickListener {
                listFood[position].minusCount()
                notifyDataSetChanged()
            }

            cbCheck.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateFoodCheck(listFood[position],isChecked)
//                listFood[position].isCheck = isChecked
                if (isChecked) {
                    checkedList.add(listFood[position])
                    viewModel.isCheckFoods.postValue(checkedList)
                }
            }
        }
    }
}