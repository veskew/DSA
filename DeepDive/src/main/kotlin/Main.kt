package org.example

import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun insertionSort(list: MutableList<Int>, left: Int, right: Int) {
    for (i in (left + 1)..right) {
        val key = list[i]
        var j = i - 1
        while (j >= left && list[j] > key) {
            list[j + 1] = list[j]
            j--
        }
        list[j + 1] = key
    }
}



fun merge(list: MutableList<Int>, left: Int, mid: Int, right: Int) {
    val leftPart = list.subList(left, mid + 1).toMutableList()
    val rightPart = list.subList(mid + 1, right + 1).toMutableList()

    var i = 0
    var j = 0
    var k = left

    while (i < leftPart.size && j < rightPart.size) {
        if (leftPart[i] <= rightPart[j]) {
            list[k++] = leftPart[i++]
        }
        else {
            list[k++] = rightPart[j++]
        }
    }

    while (i < leftPart.size){
        list[k++] = leftPart[i++]
    }

    while (j < rightPart.size){
        list[k++] = rightPart[j++]
    }

}



fun detectRuns(list: MutableList<Int>): MutableList<Pair<Int, Int>> {
    val runs = mutableListOf<Pair<Int, Int>>()
    val n = list.size
    var i = 0

    while (i < n) {
        val start = i
        if (i == n - 1) {
            runs.add(start to i)
            break
        }

        if (list[i + 1] < list[i]) {
            while (i + 1 < n && list[i + 1] < list[i]) {
                i++
            }
            list.subList(start, i + 1).reverse()
        }
        else {
            while (i + 1 < n && list[i + 1] >= list[i]){
                i++
            }
        }

        runs.add(start to i)
        i++
    }

    return runs
}



fun timSort(input: List<Int>): List<Int> {
    val list = input.toMutableList()
    val runs = detectRuns(list)
    val minRun = 32

    for ((start, end) in runs) {
        val right: Int = if (end - start + 1 < minRun) {
            minOf(list.size - 1, start + minRun - 1)
        }
        else end

        insertionSort(list, start, right)
    }

    var size = minRun
    while (size < list.size) {
        var left = 0
        while (left < list.size) {
            val mid = left + size - 1
            val right = minOf((left + 2 * size - 1), list.size - 1)
            if (mid < right){
                merge(list, left, mid, right)
            }
            left += 2 * size
        }
        size *= 2
    }
    return list
}



fun main() {

    val arr = listOf(7, 9, 64, 20, 57, 16, 9, 53, 14, 5, 1, 6, 3, 3, 4, 7, 2, 3, 6, 45, 6, 6, 52, 23, 65, 342, 134, 762, 234, 342, 123 ,43 , 43, 43, 53,6 ,236 ,36 ,36 ,363, 743 ,73 ,737, 643, 643, 23, 42, 743, 52, 325, 235, 724, 742, 742, 624, 724, 25, 134, 5, 54, 6, 46, 24, 76, 23 ,74)
    val sorted = timSort(arr)
    println(arr)
    println(sorted)
    val runTimes = mutableListOf<Double>()
    for (size in listOf(10, 100, 1000, 10000, 100000, 1000000)) {
        val x = (1 until size).map { Random.nextInt(100000) }
        val runTime = measureTime {
            timSort(x as MutableList<Int>)
        }
        runTimes.add(runTime.toDouble(DurationUnit.SECONDS))
    }
    println("Runtimes are $runTimes")
}