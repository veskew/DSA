package org.example


class Graph<VertexType> {
    private var vertices: MutableSet<VertexType> = mutableSetOf()
    private var edges: MutableMap<VertexType, MutableSet<VertexType>> = mutableMapOf()

    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     *   was already in the graph
     */
    fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * Add an edge between vertex [from] connecting to vertex [to]
     * @param from the vertex for the edge to originate from
     * @param to the vertex to connect the edge to
     * @return true if the edge is successfully added and false if the edge
     *     can't be added or already exists
     */
    fun addEdge(from: VertexType, to: VertexType): Boolean {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            return false
        }
        edges[from]?.also { currentAdjacent ->
            if (currentAdjacent.contains(to)) {
                return false
            }
            currentAdjacent.add(to)
        } ?: run {
            edges[from] = mutableSetOf(to)
        }
        return true
    }

    /**
     * Clear all vertices and edges
     */
    fun clear() {
        vertices = mutableSetOf()
        edges = mutableMapOf()
    }

    /**
     * Search through a graph using a breadth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return true if and only if path exists between [start] and [target]
     */
    fun bfs(start: VertexType, target: VertexType): Boolean {
        val priorityList: MutableList<VertexType> = mutableListOf()
        val toVisit: MutableSet<VertexType> = mutableSetOf()
        priorityList.add(start)
        toVisit.add(start)
        while (!priorityList.isEmpty()){
            val n: VertexType = priorityList.removeAt(0)
            if (n == target){
                return true
            }
            val connectedNodes = edges[n]
            if (connectedNodes != null) {
                for (m in connectedNodes) {
                    if (m !in toVisit) {
                        toVisit.add(m)
                        priorityList.add(m)
                    }
                }
            }
        }
        return false
    }

    /**
     * Search through a graph using a depth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return true if and only if path exists between [start] and [target]
     */
    fun dfs(start: VertexType, target: VertexType): Boolean{
        val priorityList: MutableList<VertexType> = mutableListOf()
        val toVisit: MutableSet<VertexType> = mutableSetOf()
        priorityList.add(start)
        toVisit.add(start)
        while (!priorityList.isEmpty()){
            val n: VertexType = priorityList.removeAt(0)
            if (n == target){
                return true
            }
            val connectedNodes = edges[n]
            if (connectedNodes != null) {
                for (m in connectedNodes) {
                    if (m !in toVisit) {
                        toVisit.add(m)
                        priorityList.add(m)
                    }
                }
            }
        }
        return false
    }


}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val g = Graph<String>()

    // Add vertices
    g.addVertex("A")
    g.addVertex("B")
    g.addVertex("C")
    g.addVertex("D")
    g.addVertex("E")

    // Add edges
    g.addEdge("A", "B")
    g.addEdge("A", "C")
    g.addEdge("B", "D")
    g.addEdge("C", "E")
    g.addEdge("D", "E")

    // Run BFS
    val start = "A"
    val target = "E"
    val found = g.bfs(start, target)

    println("Path from $start to $target: $found")


}