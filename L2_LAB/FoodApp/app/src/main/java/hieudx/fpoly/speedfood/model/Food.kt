package hieudx.fpoly.speedfood.model

data class Food(
    var name: String,
    var desc: String,
    var img: Int,
    var count: Int,
    var isCheck: Boolean
) {
    fun plusCount() {
        count++
    }

    fun minusCount() {
        if (count > 0) {
            count--
        }
    }
}