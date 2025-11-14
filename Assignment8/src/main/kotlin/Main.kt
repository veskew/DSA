package org.example

import kotlin.math.pow
import kotlin.time.Duration
import kotlin.random.Random
import kotlin.time.measureTime


data class KDNode(val value: List<Double>, var left: KDNode?, var right: KDNode?)

fun euclidean(pointOne: List<Double>, pointTwo: List<Double>): Double {
    var temp = 0.0
    for (i in 0..<pointOne.size){
        temp += (pointOne[i] - pointTwo[i]).pow(2)
    }
    return temp.pow(.5)
}

class MyKDTree(): KDTree {
    var head: KDNode? = null
    override fun construct(points: List<List<Double>>) {
        head = splitOnDimension(points, 0, points[0].size)
    }

    private fun splitOnDimension(points: List<List<Double>>, currentDimension: Int, dimensions: Int): KDNode? {
        if (points.isEmpty()){
            return null
        }
        else if (points.size == 1){
            return KDNode(points[0], null, null)
        }
        else {
            val sort = points.sortedBy { it[currentDimension]}
            val n = points.size / 2
            val nextDimension: Int = if (currentDimension == dimensions - 1){
                0
            } else {
                currentDimension + 1
            }

            return KDNode(sort[n], splitOnDimension(sort.slice(0..<n), nextDimension, dimensions), splitOnDimension(sort.slice((n+1)..<points.size), nextDimension, dimensions))
        }

    }


    private fun nearest(node: KDNode?, target: List<Double>, depth: Int, best: Pair<List<Double>, Double>?): Pair<List<Double>, Double>? {
        if (node == null) return best

        val k = target.size
        val axis = depth % k

        val d = euclidean(node.value, target)
        var currentBest = best
        if (currentBest == null || d < currentBest.second) {
            currentBest = Pair(node.value, d)
        }

        val goRight = target[axis] >= node.value[axis]

        val (primary, secondary) =
            if (goRight) Pair(node.right, node.left) else Pair(node.left, node.right)

        currentBest = nearest(primary, target, depth + 1, currentBest)

        val distanceToPlane = kotlin.math.abs(target[axis] - node.value[axis])
        if (currentBest != null && distanceToPlane < currentBest.second) {
            currentBest = nearest(secondary, target, depth + 1, currentBest)
        }

        return currentBest
    }

    override fun search(target: List<Double>): List<Double> {
        val best = nearest(head, target, 0, null)
        return best!!.first
    }

}

fun bruteSearch(points: List<List<Double>>, target: List<Double>): List<Double>{
    var closest = euclidean(points[0], target)
    var result = points[0]
    for (i in 1..<points.size){
        if (euclidean(points[i], target) < closest) {
            result = points[i]
            closest = euclidean(points[i], target)
        }
    }
    return result
}

/**
 * Benchmark [KDTree] against brute force nearest neighbor.
 * 1000 test points will be generated to test against the training
 * points.
 *
 * @param k: the dimensionality of the dataset to create
 * @param numPoints: the number of points to use to match against
 *   (1000 test points will be used)
 * @return the triple of [Duration] objects where the first specifies
 * the time to build the tree, the second specifies the time it takes
 * to query the tree with 1,000 points, and the third is the time it
 * takes to find the nearest neighbor to these points using the brute
 * force approach.
 */
fun runExperiment(k: Int, numPoints: Int): Triple<Duration, Duration, Duration> {


    val trainingPoints = List(numPoints) {
        List(k) { Random.nextDouble(-1000.0, 1000.0) }
    }

    val targetPoints = List(1000) {
        List(k) { Random.nextDouble(-1000.0, 1000.0) }
    }

    val kdTree = MyKDTree()
    val buildTime = measureTime {
        kdTree.construct(trainingPoints)
    }

    val kdSearchTime = measureTime {
        for (target in targetPoints) {
            kdTree.search(target)
        }
    }

    val bruteSearchTime = measureTime {
        for (target in targetPoints){
            bruteSearch(trainingPoints, target)
        }

    }
    return Triple(buildTime, kdSearchTime, bruteSearchTime)
}

fun main() {
    val dimensions = listOf(10, 100, 1000, 10000)
    val points = listOf(10, 100, 1000, 10000)
    for (dimension in dimensions){
        for (point in points){
            println("Dimensions: " + dimension + " Points: " + point + " Runtimes: " + runExperiment(dimension, point))
        }
    }

}