package org.example

/**
 * Represents a K-D-Tree
 */
interface KDTree {
    /**
     * Given a list of points in k dimensions, construct a K-D Tree
     * @param points the points to be inserted into K-D Tree
     */
    fun construct(points: List<List<Double>>)

    /**
     * Given a point, find it's nearest neighbour
     * @param target the new point to find a neighbour for
     * @return The nearest neighbour to target
     */
    fun search(target: List<Double>): List<Double>

}