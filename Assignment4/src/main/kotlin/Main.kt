package org.example
import kotlin.random.Random
import kotlin.time.measureTime
import kotlin.time.DurationUnit

fun selectionSort (list: MutableList<Int>): List<Int> {
    var temp: Int = 0
    while (temp < list.size){
        val tempList = list.subList(temp, list.size)
        val targetIndex = tempList.withIndex().minByOrNull { it.value }?.index
        val tempVal = list[temp]
        list[temp] = list[targetIndex?.plus(temp) ?: 0]
        list[targetIndex?.plus(temp) ?: 0] = tempVal
        temp++
    }
    return list
}


fun merge (list: MutableList<Int>, left: Int, middle: Int, right: Int) {
    val leftSize = middle - left + 1
    val rightSize = right - middle
    val tempLeft: MutableList<Int> = list.subList(left, middle + 1).toMutableList()
    val tempRight: MutableList<Int> = list.subList(middle + 1, right + 1).toMutableList()

    var i = 0
    var j = 0
    var l = left

    while (i < leftSize && j < rightSize){
        if (tempLeft[i] < tempRight[j]){
            list[l] = tempLeft[i]
            i++
        }
        else{
            list[l] = tempRight[j]
            j++
        }
        l++
    }

    while (i < leftSize) {
        list[l] = tempLeft[i]
        i++
        l++
    }

    while (j < rightSize) {
        list[l] = tempRight[j]
        j++
        l++
    }
}


fun mergeSort (list: MutableList<Int>, left: Int, right: Int) {
    if (left < right) {
        val middle = (right + left) / 2
        mergeSort(list, left, middle)
        mergeSort(list, middle + 1, right)
        merge(list, left, middle, right)
    }

}
fun partition(list: MutableList<Int>, start: Int, end: Int): Int {
    val part = list[start]
    var i = start + 1
    for (j in start + 1 .. end) {
        if (list[j] < part) {
            val temp = list[j]
            list[j] = list[i]
            list[i] = temp
            i++
        }
    }
    list[start] = list[i - 1]
    list[i - 1] = part
    return i - 1
}

fun quickSort (list: MutableList<Int>, start: Int, end: Int) {
    if (start < end) {
        val pivotPosition = partition(list, start, end)
        quickSort(list, start, pivotPosition - 1)
        quickSort(list, pivotPosition + 1, end)
    }
}

fun insertionSort(list: MutableList<Int>): MutableList<Int> {
    for (i in 0 until list.size){
        var temp = i
        while (temp > 0 && list[temp] < list[temp - 1]){
            val tempVal = list[temp]
            list[temp] = list[temp - 1]
            list[temp - 1] = tempVal
            temp--
        }
    }
    return list
}

fun main() {


    val runTimes = mutableListOf<Double>()
    for (size in listOf(10, 100, 1000, 10000, 100000, 1000000)) {
        val x = (1 until size).map { Random.nextInt(100000) }
        val runTime = measureTime {
            quickSort(x as MutableList<Int>, 0, x.size - 1)
        }
        runTimes.add(runTime.toDouble(DurationUnit.SECONDS))
    }
    println("Runtimes are $runTimes")


    val testList: MutableList<Int> = mutableListOf(1, 7, 6, 3, 4, 8, 2, 1, 9, 0)
    println(selectionSort(testList))
    println(insertionSort(testList))
    mergeSort(testList, 0, testList.size - 1)
    println(testList)
    val testList2: MutableList<Int> = mutableListOf(1, 7, 6, 3, 4, 8, 2, 1, 9, 0)
    quickSort(testList2, 0, testList.size - 1)
    println(testList2)

}