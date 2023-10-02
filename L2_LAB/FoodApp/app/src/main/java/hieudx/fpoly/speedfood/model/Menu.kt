package hieudx.fpoly.speedfood.model

import hieudx.fpoly.speedfood.R

object Menu {
    fun getMainCourseList(): ArrayList<Food> {
        val mainCourseList = ArrayList<Food>()
        mainCourseList.add(Food("Cơm rang rưa bò", "Free ship 3km", R.drawable.img, 1, false))
        mainCourseList.add(Food("Phở bò", "Mua 2 tặng 1", R.drawable.img_1, 1, false))
        mainCourseList.add(Food("Cơm rang cải bò", "Tặng 1 coca", R.drawable.img_2, 1, false))
        mainCourseList.add(Food("Mì xào hải sản", "Khuyến mãi 30%", R.drawable.img_3, 1, false))
        mainCourseList.add(Food("Bún bò huế", "Tặng thêm chân giò", R.drawable.img_4, 1, false))
        mainCourseList.add(Food("Bún chả", "Tặng thêm chả", R.drawable.img_5, 1, false))
        mainCourseList.add(Food("Bún đậu mắm tôm", "Tặng 1 nước đậu", R.drawable.img_6, 1, false))
        mainCourseList.add(Food("Bún cá cay", "Khuyến mãi thêm cá", R.drawable.img_7, 1, false))
        return mainCourseList
    }

    fun getSideDishesList(): ArrayList<Food> {
        val sideDishesList = ArrayList<Food>()
        sideDishesList.add(Food("Món phụ", "Free ship 3km", R.drawable.img, 1, false))
        sideDishesList.add(Food("Món phụ", "Mua 2 tặng 1", R.drawable.img_1, 1, false))
        sideDishesList.add(Food("Món phụ", "Tặng 1 coca", R.drawable.img_2, 1, false))
        sideDishesList.add(Food("Món phụ", "Khuyến mãi 30%", R.drawable.img_3, 1, false))
        sideDishesList.add(Food("Món phụ", "Tặng thêm chân giò", R.drawable.img_4, 1, false))
        sideDishesList.add(Food("Món phụ", "Tặng thêm chả", R.drawable.img_5, 1, false))
        sideDishesList.add(Food("Món phụ", "Tặng 1 nước đậu", R.drawable.img_6, 1, false))
        sideDishesList.add(Food("Món phụ", "Khuyến mãi thêm cá", R.drawable.img_7, 1, false))
        return sideDishesList
    }

    fun getDessertList(): ArrayList<Food> {
        val dessertList = ArrayList<Food>()
        dessertList.add(Food("Đồ uống", "Free ship 3km", R.drawable.img, 1, false))
        dessertList.add(Food("Đồ uống", "Mua 2 tặng 1", R.drawable.img_1, 1, false))
        dessertList.add(Food("Đồ uống", "Tặng 1 coca", R.drawable.img_2, 1, false))
        dessertList.add(Food("Đồ uống", "Khuyến mãi 30%", R.drawable.img_3, 1, false))
        dessertList.add(Food("Đồ uống", "Tặng thêm chân giò", R.drawable.img_4, 1, false))
        dessertList.add(Food("Đồ uống", "Tặng thêm chả", R.drawable.img_5, 1, false))
        dessertList.add(Food("Đồ uống", "Tặng 1 nước đậu", R.drawable.img_6, 1, false))
        dessertList.add(Food("Đồ uống", "Khuyến mãi thêm cá", R.drawable.img_7, 1, false))
        return dessertList
    }

    fun getBeveragesList(): ArrayList<Food> {
        val beveragesList = ArrayList<Food>()
        beveragesList.add(Food("Đồ uống", "Free ship 3km", R.drawable.img, 1, false))
        beveragesList.add(Food("Đồ uống", "Mua 2 tặng 1", R.drawable.img_1, 1, false))
        beveragesList.add(Food("Đồ uống", "Tặng 1 coca", R.drawable.img_2, 1, false))
        beveragesList.add(Food("Đồ uống", "Khuyến mãi 30%", R.drawable.img_3, 1, false))
        beveragesList.add(Food("Đồ uống", "Tặng thêm chân giò", R.drawable.img_4, 1, false))
        beveragesList.add(Food("Đồ uống", "Tặng thêm chả", R.drawable.img_5, 1, false))
        beveragesList.add(Food("Đồ uống", "Tặng 1 nước đậu", R.drawable.img_6, 1, false))
        beveragesList.add(Food("Đồ uống", "Khuyến mãi thêm cá", R.drawable.img_7, 1, false))
        return beveragesList
    }

}