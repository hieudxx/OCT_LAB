package hieudx.fpoly.speedfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hieudx.fpoly.speedfood.model.Food
import hieudx.fpoly.speedfood.model.Menu

class FoodViewModel : ViewModel() {

    var maiCourseList = MutableLiveData<ArrayList<Food>>()
    var sideDishesList = MutableLiveData<ArrayList<Food>>().postValue(Menu.getSideDishesList())
    var dessertList = MutableLiveData<ArrayList<Food>>().postValue(Menu.getDessertList())
    var beveragesList = MutableLiveData<ArrayList<Food>>().postValue(Menu.getBeveragesList())

    val foods = MutableLiveData<ArrayList<Food>>()
    val isCheckFoods = MutableLiveData<ArrayList<Food>>()

}