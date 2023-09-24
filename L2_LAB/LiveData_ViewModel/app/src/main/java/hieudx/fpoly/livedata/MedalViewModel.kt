package hieudx.fpoly.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedalViewModel : ViewModel() {
//    cho các giá trị vàng, bạc, đồng trở thành livedata
    var gold: MutableLiveData<Int> = MutableLiveData()
    var silver: MutableLiveData<Int> = MutableLiveData()
    var bronze: MutableLiveData<Int> = MutableLiveData()

    init {
//        khối này khởi tạo giá trị ban đầu cho các biến là 0
        gold.value = 0
        silver.value = 0
        bronze.value = 0
    }
}