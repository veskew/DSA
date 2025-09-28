package org.example
import java.io.File


/**
 * Class for implementing Euler Problem 81
 * @function findShortestPathCount solves project Euler problem 81
 */
class Euler81(){
    // unpack input file
    val numbers: DoubleArray = File("src/main/kotlin/0081_matrix.txt")
        .readLines()
        .flatMap { line ->
            line.split(",")
                .map { it.trim().toDouble() }
        }
        .toDoubleArray()
    // size of input matrix
    val size: Int = 80
    // priority queue for implementing Dijkstra's
    val toVisit: MyMinPriorityQueue<Int> = MyMinPriorityQueue()
    // node to start at
    val start: Int = 0
    // target node to find the shortest path too.
    val target: Int = 80 * 80 - 1
    // function for solving this problem
    fun findShortPathCount(): Int{
        // add the top left element of the matrix to the priority queue
        toVisit.addWithPriority(start, numbers[start])
        // loop as long as pq isn't empty
        while (!toVisit.isEmpty()) {
            // pop the lowest priority element
            val current: Pair<Int, Double>? = toVisit.next()
            // null check
            if (current != null) {
                // check if the bottom right element of matrix was popped
                if (current.first == target)
                    return current.second.toInt()
                // list of children of popped nodes
                val children: MutableList<Int> = mutableListOf()
                // check if children are in bound
                if (current.first % size != size - 1)
                    children.add(current.first + 1)
                if (current.first + size < numbers.size)
                    children.add(current.first + size)
                //loop through all children, add them or modify pq priorities as necessary
                for (child in children){
                    val childPriority: Double = toVisit.contains(child)
                    val newPriority: Double = numbers[child] + current.second
                    if (childPriority == -1.0)
                        toVisit.addWithPriority(child, numbers[child] + current.second)
                    else if (newPriority < childPriority)
                        toVisit.adjustPriority(child, newPriority)
                }


            }


        }
        return 0
    }
}