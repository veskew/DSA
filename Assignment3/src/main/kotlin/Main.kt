package org.example


class MyMinPriorityQueue<T>: MinPriorityQueue<T> {
    val pqheap: MinHeap<T> = MinHeap()
    override fun isEmpty(): Boolean {
        return pqheap.isEmpty()
    }

    override fun addWithPriority(elem: T, priority: Double) {
        pqheap.insert(elem, priority)
    }

    override fun next(): Pair<T, Double>? {
        return pqheap.getMin()
    }

    override fun adjustPriority(elem: T, newPriority: Double) {
        pqheap.adjustHeapNumber(elem, newPriority)
    }

    override fun contains(elem: T): Double{
        return pqheap.findHeapNumber(elem)
    }
}


class MyGraph<VertexType>: Graph<VertexType> {

    var graphSet : MutableSet<VertexType> = mutableSetOf()
    var edgeMap : MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    override fun getVertices(): Set<VertexType> {
        return graphSet
    }

    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        graphSet.add(from)
        graphSet.add(to)
        val neighbors = edgeMap.getOrPut(from) { mutableMapOf() }
        neighbors[to] = cost
    }

    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        return edgeMap[from] ?: emptyMap()
    }

    override fun clear() {
        edgeMap = mutableMapOf()
        graphSet = mutableSetOf()
    }

}

fun main() {

    val problem1: Euler81 = Euler81()
    println(problem1.findShortPathCount())
}