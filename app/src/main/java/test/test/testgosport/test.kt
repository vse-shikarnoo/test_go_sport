package test.test.testgosport

data class Test(
    val val1: Int,
    val val2: Int,
    val val3: Int,
    val val4: Int
) {
    fun getMap() = mapOf(Pair(val1, val2), Pair(val3, val4))
}

fun main() {
    val test = Test(1,2,3,4)

    println(test.getMap())

}