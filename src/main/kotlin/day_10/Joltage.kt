package day_10

data class JoltageDifference(val oneJoltCount: Int, val threeJoltCount: Int)

fun getJoltageDifference(joltageList: List<Int>): JoltageDifference{
    return JoltageDifference(0,0)
}