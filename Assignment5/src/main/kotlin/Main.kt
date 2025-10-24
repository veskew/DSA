package org.example

import sun.security.ec.point.ProjectivePoint
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.measureTime


class MyMatrix(val size: Int) {
    val data: Array<DoubleArray> = Array(size) { DoubleArray(size) }
    /**
     * Prints out [this] legibly to command line.
     */
    operator fun invoke(){
        for (i in 0..<size) {
            for (j in 0..<size) {
                print(this[i, j])
                print("|")
            }
            println("")
            for (j in 0..<size){
                print("----")
            }
            println("")
        }
    }

    /**
     * Get value at [row] and [col] of [this]
     * @return value at [row] and [col]
     */
    operator fun get(row: Int, col: Int): Double {
        return data[row][col]
    }

    /**
     * Modify value at [row] and [col] of [this]
     */
    operator fun set(row: Int, col: Int, value: Double){
        data[row][col] = value
    }

    /**
     * Add [this] matrix to [other].
     * @return [this] + [other]
     */
    operator fun plus(other: MyMatrix):MyMatrix{
        val result = MyMatrix(size)
        for (i in 0..<size){
            for (j in 0..<size){
                result[i, j] = this[i, j] + other[i, j]
            }
        }
        return result
    }
    /**
     * subtract [this] matrix by [other].
     * @return a new MyMatrix with the completed subtraction.
     */
    operator fun minus(other: MyMatrix):MyMatrix{
        val result = MyMatrix(size)
        for (i in 0..<size){
            for (j in 0..<size){
                result[i, j] = this[i, j] - other[i, j]
            }
        }
        return result
    }
    /**
     * Split [this] matrix into four size/2 matrices.
     * @return List of split matrices.
     */
    fun divide(): List<MyMatrix> {
        val upperLeft = MyMatrix(size/2)
        val upperRight = MyMatrix(size/2)
        val lowerLeft = MyMatrix(size/2)
        val lowerRight = MyMatrix(size/2)
        val n = size/2
        for (i in 0..<size){
            for (j in 0..<size){
                if (i < n && j < n){
                    upperLeft[i, j] = this[i, j]
                }
                else if (i >= n && j < n){
                    lowerLeft[i - n, j] = this[i, j]
                }
                else if (i < n){
                    upperRight[i, j - n] = this[i, j]
                }
                else {
                    lowerRight[i - n, j - n] = this[i, j]
                }
            }
        }
        return listOf(upperLeft, upperRight, lowerLeft, lowerRight)
    }

    /**
     * Multiply [this] matrix by [other].
     * You can implement this either using block-based matrix multiplication or
     * traditional matrix multiplication (the kind you learn about in math
     * classes!)
     * @return [this]*[other] if the dimensions are compatible and null otherwise
     */
    fun multiply(other: MyMatrix):MyMatrix {
        val mat1 = MyMatrix(size)
        for (i in 0..<(other.size)){
            for (j in 0..<(other.size)){
                var result = 0.0
                for (k in 0..<(other.size)){
                    result += data[i][k] * other[k, j]
                }
                mat1[i, j] = result
            }
        }
        return mat1
    }

    /**
     * Multiply [this] matrix by [other].
     * Your code should use Strassen's algorithm
     * @return [this]*[other] if the dimensions are compatible and null otherwise
     */
    fun strassenMultiply(other: MyMatrix):MyMatrix {
        val result = MyMatrix(size)
        if (size <= 32){
            return this.multiply(other)
        }
        val thisList = this.divide()
        val otherList = other.divide()
        val M1 = (thisList[0] + thisList[3]).strassenMultiply(otherList[0] + otherList[3])
        val M2 = (thisList[2] + thisList[3]).strassenMultiply(otherList[0])
        val M3 = (thisList[0]).strassenMultiply(otherList[1] - otherList[3])
        val M4 = (thisList[3]).strassenMultiply(otherList[2] - otherList[0])
        val M5 = (thisList[0] + thisList[1]).strassenMultiply(otherList[3])
        val M6 = (thisList[2] - thisList[0]).strassenMultiply(otherList[0] + otherList[1])
        val M7 = (thisList[1] - thisList[3]).strassenMultiply(otherList[2] + otherList[3])
        val upperLeft = M1 + M4 - M5 + M7
        val upperRight = M3 + M5
        val lowerLeft = M2 + M4
        val lowerRight = M1 - M2 + M3 + M6
        val n = size/2
        for (i in 0..<size){
            for (j in 0..<size){
                if (i < n && j < n){
                    result[i, j] = upperLeft[i, j]
                }
                else if (i >= n && j < n){
                    result[i, j] = lowerLeft[i - n, j]
                }
                else if (i < n){
                    result[i, j] = upperRight[i, j - n]
                }
                else {
                    result[i, j] = lowerRight[i - n, j - n]
                }
            }
        }
        return result

    }


}
data class TwoStrings(val string1: String, val string2: String){}


fun needlemanWunsch(snippet: String, target: String): TwoStrings {
    val n1: Int = snippet.length
    val n2: Int = target.length
    val matchArray: Array<IntArray> = Array(n1 + 1) { IntArray(n2 + 1) }
    for (i in 0 ..maxOf(n1, n2)) {
        if (i <= n1) {
            matchArray[i][0] =  -i
        }
        if (i <= n2) {
            matchArray[0][i] = -i
        }
    }
    for (row in 1..<n1){
        for (col in 1..<n2){
            val match: Boolean = snippet[row] == target[col]
            val diag: Int
            if (match)
                diag = 1 + matchArray[row - 1][col - 1]
            else
                diag = -1 + matchArray[row - 1][col - 1]
            val left: Int = -2 + matchArray[row][col - 1]
            val right: Int = -2 + matchArray[row - 1][col]
            matchArray[row][col] = maxOf(diag, left, right)
        }
    }
    var result1 = ""
    var result2 = ""
    var row = 0
    var col = 0
    while (row < (n1 - 1) && col < (n2 - 1)){
        var down = 0
        if (row < (n1 - 1)) {
            down = matchArray[row + 1][col]
        }
        var right = 0
        if (col < (n2 - 1)) {
            right = matchArray[row][col + 1]
        }
        var diag = 0
        if (col < (n2 - 1) && row < (n1 - 1)){
            diag = matchArray[row + 1][col + 1]
        }
        if (down > right && down > diag) {
            result1 += snippet[row]
            result2 += "-"
            row++
        }
        else if (right > diag) {
            result1 += "-"
            result2 += target[col]
            col++
        }
        else {
            result1 += snippet[row]
            result2 += target[col]
            row++
            col++
        }
    }
    return TwoStrings(result1, result2)
}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // Use Optimize for somewhat similar sequences @ https://blast.ncbi.nlm.nih.gov/Blast.cgi?PROGRAM=blastn&BLAST_SPEC=GeoBlast&PAGE_TYPE=BlastSearch

    val genomeSnippet = "TGGCGACAACCGTAGCGGAATATTTTCGCGACCAGGGAAAACGGGTCGTGCTTTTTATCGATTCCATGACCCGTTATGCGCGTGCTTTGCGAGACGTGGCACTGGCGTCGGGAGAGCGTCCGGCTCGTCGAGGTTATCCCGCCTCCGTATTCGATAATTTGCCCCGCTTGCTGGAACGCCCAGGGGCGACCAGCGAGGGAAGCATTACTGCCTTTTATACGGTACTGCTGGAAAGCGAGGAAGAGGCGGACCCGATGGCGGATGAAATTCGCTCTATCCTTGACGGTCACCTGTATCTGAGCAGAAAGCTGGCCGGGCAGGGACATTACCCGGCAATCGATGTACTGAAAAGCGTAAGCCGCGTTTTT"
    val targetGenome = "GGATCCGACAGGGAAAATCGTTGAGCGTTTTACCCCTGAAGTGGCGCCGATTAGCGAAGAACGCGTTATTGATGTCGCACCGCCGTCTTACGCTTCACGCGTTGGCGTCCGTGAACCGCTGATTACCGGTGTGCGCGCGATTGACGGGTTATTGACCTGTGGCGTAGGCCAGCGAATGGGCATTTTTGCCTCCGCAGGATGCGGTAAGACCATGCTGATGCATATGCTGATCGAGCAAACGGAGGCGGATGTCTTTGTTATCGGTCTTATCGGTGAACGAGGCCGTGAGGTCACTGAATTCGTGGATATGTTGCGCGCTTCGCATAAGAAAGAAAAATGCGTGCTGGTTTTTGCCACTTCCGATTTCCCCTCGGTCGATCGCTGCAATGCGGCGCAACTGGCGACAACCGTAGCGGAATATTTTCGCGACCAGGGAAAACGGGTCGTGCTTTTTATCGATTCCATGACCCGTTATGCGCGTGCTTTGCGAGACGTGGCACTGGCGTCGGGAGAGCGTCCGGCTCGTCGAGGTTATCCCGCCTCCGTATTCGATAATTTGCCCCGCTTGCTGGAACGCCCAGGGGCGACCAGCGAGGGAAGCATTACTGCCTTTTATACGGTACTGCTGGAAAGCGAGGAAGAGGCGGACCCGATGGCGGATGAAATTCGCTCTATCCTTGACGGTCACCTGTATCTGAGCAGAAAGCTGGCCGGGCAGGGACATTACCCGGCAATCGATGTACTGAAAAGCGTAAGCCGCGTTTTTGGACAAGTCACGACGCCGACACATGCTGAACAGGCATCTGCCGTGCGTAAATTAATGACGCGTTTGGAAGAGCTCCAGCTTTTCATTGACTTGGGAGAATATCGTCCTGGCGAAAATATCGATAACGATCGGGCGATGCAGATGCGGGATAGCCTGAAAGCCTGGTTATGCCAGCCGGTAGCGCAGTATTCATCCTTTGATGACACGTTGAGCGGTATGAATGCATTCGCTGACCAGAATTAAAGTATTGCAGCGGCGCTGTACGGTATTTCATTCACAGTGTGAGTCGATATTACTTCGCTATCAGGATGAGGACCGCGGGCTGCAGGCCGAGGAGGAGGCGATCCTTGAACAAATAGCGGGTCTGAAATTGTTATTAGATACGCTGCGTGCAGAAAACAGACAGCTCAGTCGTGAGGAAATTTATACGTTATTACGTAAGCAGTCTATTGTTCGCCGGCAGATAAAAGATTTAGAACTCCAGATTATACAAATTCAGGAAAAACGGAGCGAGCTGGAAAAGAAAAGGGAAGAGTTTCAGAAAAAAAGTAAATATTGGTTGCGCAAAGAAGGGAACTATCAACGCTGGATAATCCGTCAGAAAAGATTCTATATCCAGCGAGAGATACAGCAGGAAGAGGCCGAGTCAGAGGAGATAATTTAATGGGCGATGTGTCAGCTGTCAGTTCATCCGGGAACATTTTACTGCCGCAGCAGGATGAGGTTGGCGGTTTATCAGAAGCATTAAAAAAAGCGGTGGAAAAACATAAGACAGAATATTCCGGTGATAAAAAAGATCGCGACTATGGCGATGCTTTCGTAATGCATAAAGAAACGGCTTTACCGTTATTACTGGCGGCATGGCGACATGGCGCGCCAGCGAAATCAGAACATCACAATGGCAACGTTTCTGGTCTGCATCATAACGGAAAAAGCGAACTCAGGATTGCTGAAAAACTGTTGAAAGTCACTGCTGAAAAATCTGTCGGTTTGATCTCTGCGGAGGCCAAAGTAGATAAATCCGCAGCGTTGCTATCGTCTAAAAATAGGCCGTTAGAAAGCGTAAGCGGTAAAAAATTATCTGCTGATTTAAAAGCTGTGGAATCCGTTAGTGAAGTAACCGATAACGCCACGGGAATCTCTGACGATAATATCAAGGCATTGCCTGGGGATAATAAAGCCATCGCGGGCGAAGGCGTTCGTAAAGAGGGCGCGCCGCTGGCGCGGGATGTCGCACCTGCCCGAATGGCCGCAGCCAATACCGGTAAGCCTGAAGATAAAGATCATAAAAAGGTTAAAGATGTTTCTCAGCTTCCGCTGCAACCAACCACTATCGCCGATCTTAGCCAATTAACCGGCGGCGATGAAAAAATGCCTTTAGCGGCGCAATCAAAGCCGATGATGACTATTTTTCCCACTGCCGATGGCGTGAAAGGAGAGGATAGCTCGCTGACTTACCGTTTTCAGCGCTGGGGAAATGACTATTCCGTCAATATTCAGGCGCGGCAAGCAGGGGAGTTTTCGTTAATACCGTCAAATACGCAGGTTGAACATCGTTTGCATGATCAATGGCAAAACGGTAATCCCCAGCGCTGGCACCTGACGCGAGACGATCAACAAAATCCGCAGCAGCAACAGCACAGACAGCAATCTGGCGAGGAGGATGACGCCTGATGTCATTGCGTGTGAGACAGATTGATCGTCGCGAATGGCTATTGGCGCAAACCGCGACAGAATGCCAGCGCCATGGCCGGGAAGCGACGCTGGAATATCCGACGCGACAGGGAATGTGGGTTCGGTTGAGCGATGCAGAAAAACGGTGGTCGGCCTGGATTAAACCTGGGGACTGGCTTGAGCATGTCTCTCCCGCTCTGGCTGGGGCGGCGGTTTCTGCTGGCGCTGAGCACCTGGTCGTTCCCTGGCTTGCTGCAACAGAGCGACCGTTTGAGTTGCCCGTGCCGCATTTGTCCTGTCGGCGTTTATGCGTAGAGAACCCCGTACCGGGAAGCGCGCTGCCGGAAGGGAAATTGTTGCACATTATGAGCGATCGGGGCGGCCTGTGGTTTGAGCATCTTCCTGAACTGCCTGCAGTCGGGGGCGGCAGGCCGAAAATGCTGCGTTGGCCGTTGCGCTTTGTAATCGGTAGCAGTGATACGCAGCGTTCGTTGCTGGGCCGAATCGGGATCGGAGATGTACTCCTGATTCGTACTTCCCGTGCGGAAGTTTATTGCTACGCGAAAAAGTTAGGTCATTTCAACCGTGTTGAAGGGGGAATTATTGTGGAAACGTTAGATATTCAACATATCGAAGAAGAAAATAATACAACTGAAACTGCAGAAACTCTGCCTGGCTTGAATCAATTGCCCGTCAAACTGGAATTTGTTTTGTATCGTAAGAACGTTACCCTCGCCGAACTCGAAGCCATGGGGCAGCAACAGCTATTATCACTGCCGACCAATGCTGAACTTAACGTTGAAATTATGGCGAATGGTGTTTTGCTGGGTAATGGCGAACTGGTACAGATGAATGACACCTTAGGCGTTGAGATCCATGAATGGCTGAGCGAGTCTGGTAATGGGGAATGATATCTCATTAATTGCCTTACTGGCATTTTCCACCCTGTTGCCATTTATTATTGCGTCAGGAACCTGTTTCGTTAAATTTTCTATTGTATTTGTCATGGTGCGTAACGCCCTGGGATTACAGCAGATACCTTCAAATATGACGCTTAACGGCGTCGCATTGCTGCTTTCTATGTTTGTTATGTGGCCCATAATGCATGATGCCTACGTCTATTTTGAGGACGAAGATGTCACCTTTAATGATATTTCATCATTAAGTAAACACGTTGATGAAGGTCTGGATGGTTATCGCGATTATCTGATCAAATATTCAGATCGCGAGTTAGTTCAGTTTTTTGAAAACGCGCAACTGAAGCGTCAGTATGGAGAAGAGACCGAGACGGTAAAGCGTGACAAAGATGAAATTGAAAAACCTTCAATATTTGCGTTATTACCTGCTTATGCGCTGAGCGAAATAAAAAGCGCGTTTAAAATTGGTTTTTATCTTTATTTGCCCTTTGTCGTCGTCGACCTGGTGGTATCCAGCGTGCTACTGGCGCTGGGGATGATGATGATGAGTCCGGTGACGATATCTACACCTATTAAGCTGGTGCTTTTTGTCGCGCTTGATGGCTGGACCTTACTGTCTAAGGGATTGATATTACAGTATATGGACATTGCAACATGACATCATTACGAGACGGGATAGTTAAATGGATGATTTAGTGTTTGCAGGTAATAAGGCGCTCTATCTTGTTTTGATCCTGTCAGGGTGGCCGACGATTGTCGCAACGATTATCGGCCTCCTGGTAGGGTTATTCCAGACGGTAACGCAATTACAGGAACAGACGCTGCCTTTTGGCATTAAATTACTTGGCGTGTGTTTATGCTTGTTTTTACTGTCTGGCTGGTATGGCGAAGTTTTACTCTCTTACGGGCGTCAGGTGATATTCCTGGCGTTGGCTAAGGGGTAAAAAATGTTTTACGCGTTGTACTTTGAAATTCATCACCTGGTTGCGTCTGCGGCGCTAGGGTTTGCTCGCGTGGCGCCGATTTTTTTCTTCCTGCCGTTTTTGAATAGCGGGGTATTAAGCGGTGCGCCGAGAAACGCCATTATCATCCTGGTGGCATTGGGAGTATGGCCGCATGCATTGAACGAGGCGCCGCCGTTTTTATCGGTGGCGATGATCCCGTTAGTTCTGCAAGAAGCGGCGGTAGGCGTCATGCTGGGCTGTCTGCTGTCATGGCCTTTTTGGGTTATGCATGCGCTGGGTTGTATTATCGATAACCAGCGAGGGGCAACGCTAAGTAGTAGTATCGATCCGGCAAACGGTATTGATACCTCGGAAATGGCTAATTTCCTGAATATGTTTGCCGCTGTCGTTTATTTACAAAACGGCGGTCTGGTCACGATGGTTGACGTGTTAAATAAAAGCTATCAGCTATGCGATCCGATGAACGAGTGCACGCCTTCATTACCGCCGCTATTAACGTTTATTAATCAGGTGGCTCAAAACGCCTTGGTTCTGGCCAGTCCGGTGGTATTAGTGCTGTTGCTGTCAGAAGTATTCCTGGGTTTATTGTCGCGCTTTGCTCCGCAAATGAACGCTTTTGCGATTTCACTGACGGTAAAAAGCGGTATTGCCGTTTTAATTATGCTGCTTTATTTCTCTCCGGTACTACCGGACAATGTACTGCGACTCTCTTTCCAGGCCACAGGGTTAAGCAGTTGGTTTTACGAGCGAGGGGCGACGCATGTCCTCGAATAAAACAGAAAAACCGACTAAAAAACGGCTGGAAGACTCCGCTAAAAAAGGCCAGTCATTTAAAAGTAAAGATCTCATTATCGCCTGCCTGACGCTGGGAGGAATTGCCTATCTGGTGTCGTATGGCTCATTTAATGAGTTTATGGGGATAATTAAGATCATTATTGCGGATAATTTTGATCAGAGCATGGCTGACTACAGTTTGGCCGTTTTTGGGATAGGGTTAAAATATCTGATTCCATTTATGCTGCTCTGCTTAGTGTGTTCCGCATTACCGGCGTTATTACAGGCCGGTTTTGTGCTGGCGACAGAAGCATTAAAGCCTAATTTATCGGCGTTAAACCCGGTAGAAGGGGCAAAAAAACTTTTTAGTATGCGCACGGTTAAAGATACGGTCAAAACCCTACTGTATCTCTCATCCTTTGTGGTGGCCGCCATCATTTGCTGGAAGAAATATAAGGTTGAAATCTTTTCTCAGCTAAATGGCAATATTGTAGGTATTGCCGTCATTTGGCGTGAACTTCTCCTCGCATTGGTATTAACTTGCCTTGCTTGCGCATTGATTGTCTTATTATTGGATGCTATTGCGGAATATTTCCTGACCATGAAAGATATGAAAATGGATAAGGAAGAAGTGAAGCGTGAAATGAAGGAGCAGGAAGGGAACCCAGAGGTTAAATCTAAAAGACGTGAAGTTCATATGGAAATTCTGTCTGAACAGGTGAAATCTGATATTGAAAACTCACGCCTGATTGTTGCCAACCCCACGCATATTACGATCGGGATTTATTTTAAACCCGAATTGATGCCGATTCCGATGATCTCGGTGTATGAAACGAATCAGCGCGCACTGGCCGTCCGCGCCTATGCGGAGAAGGTTGGCGTACCTGTGATCGTCGATATCAAACTGGCGCGCAGTCTTTTCAAAACCCATCGCCGTTATGATCTGGTGAGTCTGGAAGAAATTGATGAAGTTTTACGTCTTCTGGTTTGGCTGGAAGAGGTAGAAAACGCGGGCAAAGACGTTATTCAGCCACAAGAAAACGAGGTACGGCATTGAGCCGCGTAAGGCAGTAGCGATGTATTCATTGGGCGTTTTTTGAATGTTCACTAACCACCGTCGGGGTTTAATAACTGCATCAGATAAACGCAGTCGTTAAGTTCTACAAAGTCGGTGACAGATAACAGGAGTAAGTAATGGATTATCAAAATAATGTCAGCGAAGAACGTGTTGCGGAAATGATTTGGGATGCCGTTAGTGAAGGCGCCACGCTAAAAGACGTTCATGGGATCC"
    val testAgainst = "TGGCCACCACGATAGCAGAATTTTTTCGCGATAATGGAAAGCGAGTCGTCTTGCTTGCCGACTCACTGACGCGTTATGCCAGGGCCGCACGGGAAATCGCTCTGGCCGCCGGAGAGACCGCGGTTTCTGGAGAATATCCGCCAGGCGTATTTAGTGCATTGCCACGACTTTTAGAACGTACGGGAATGGGAGAAAAAGGCAGTATTACCGCATTTTATACGGTACTGGTGGAAGGCGATGATATGAATGAGCCGTTGGCGGATGAAGTCCGTTCACTGCTTGATGGACATATTGTACTATCCCGACGGCTTGCAGAGAGGGGGCATTATCCTGCCATTGACGTGTTGGCAACGCTCAGCCGCGTTTTT"
//
//    var n = needlemanWunsch(genomeSnippet, testAgainst)
//    var match = 0
//    var gap = 0
//    var mismatch = 0
//    for (i in 0..<n.string1.length){
//        if (n.string1[i] == n.string2[i]) {
//            match++
//        }
//        else if ((n.string1[i] == '-' || '-' == n.string2[i])){
//            gap++
//        }
//        else{
//            mismatch++
//        }
//    }
//    println("match: ${match}, mismatch: ${mismatch}, gap ${gap}")
    val runTimes = mutableListOf<Double>()
    for (size in listOf(8, 32, 128, 512, 2048)) {
        val mat1 = MyMatrix(size)
        val mat2 = MyMatrix(size)
        for (i in 0..<mat1.size){

            val x = (1 until size).map { Random.nextDouble(100000.0) }
            for (j in 0..<x.size)
                mat1[i, j] = x[j]
        }

        for (i in 0..<mat2.size){

            val x = (1 until size).map { Random.nextDouble(100000.0) }
            for (j in 0..<x.size)
                mat2[i, j] = x[j]
        }

        val runTime = measureTime {
            mat2.strassenMultiply(mat1)
        }
        runTimes.add(runTime.toDouble(DurationUnit.SECONDS))
    }
    println("Runtimes are $runTimes")

}