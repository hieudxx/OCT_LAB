package hieudx.fpoly.speedfood.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hieudx.fpoly.speedfood.databinding.ItemMainCourseRcvBinding
import hieudx.fpoly.speedfood.model.Food
import hieudx.fpoly.speedfood.viewmodel.FoodViewModel
import kotlin.math.log


class FoodAdapter(private val listFood: ArrayList<Food>, private val viewModel: FoodViewModel) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    var checkedList: ArrayList<Food> = ArrayList()
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
                notifyItemChanged(position)
            }

            tvMinus.setOnClickListener {
                listFood[position].minusCount()
                notifyItemChanged(position)
            }

            cbCheck.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedList.add(listFood[position])
                    viewModel.isCheckFoods.value?.add(listFood[position])
                    Log.e("isCheckFoods", "${viewModel.isCheckFoods.value?.size}" )

                    Log.e("isChecked", "$isChecked" )
                }else{
                    checkedList.remove(listFood[position])
                    viewModel.isCheckFoods.value?.remove(listFood[position])
                    Log.e("isCheckFoods", "${viewModel.isCheckFoods.value?.size}" )

                    Log.e("isChecked", "$isChecked" )
                }
//                viewModel.isCheckFoods.postValue(checkedList)
            }
        }
    }
}