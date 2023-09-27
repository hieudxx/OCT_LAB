package hieudx.fpoly.speedfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hieudx.fpoly.speedfood.model.Food
class FoodViewModel : ViewModel() {
    val foods = MutableLiveData<ArrayList<Food>>()
    val isCheckFoods = MutableLiveData<ArrayList<Food>>()

    fun updateFoodCheck(food: Food, isChecked: Boolean) {

        val updatedList = foods.value?.toMutableList()

        val index = foods.value?.indexOf(food)

        if(index != null) {
            updatedList?.get(index)?.isCheck = isChecked
        }
        foods.postValue(updatedList as ArrayList<Food>?)
    }
}