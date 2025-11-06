package org.example
import java.io.File
import java.util.LinkedList
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.measureTime

class MyAssociativeArray<K, V>(val prime: Int): AssociativeArray<K, V>{

    private val bucketList = List(prime) { LinkedList<Pair<K, V>>() }
    private var count = 0
    override fun set(k: K, v: V) {
        val bucket = hash(k)
        val targetList = bucketList[bucket]
        for (i in 0..<targetList.size){
            val pair = targetList[i]
            if (pair.first == k){
                targetList[i] = Pair(k, v)
                return
            }
        }
        targetList.add(Pair(k, v))
        count++
    }

    override fun contains(k: K): Boolean {
        val bucket = hash(k)
        for (pair in bucketList[bucket]) {
            if (pair.first == k)
                return true
        }
        return false
    }

    override fun get(k: K): V? {
        val bucket = hash(k)
        for (pair in bucketList[bucket]) {
            if (pair.first == k)
                return pair.second
        }
        return null
    }

    override fun remove(k: K): Boolean {
        val bucket = hash(k)
        val targetList = bucketList[bucket]
        for (i in 0..<targetList.size){
            val pair = targetList[i]
            if (pair.first == k){
                targetList.removeAt(i)
                count--
                return true
            }

        }
        return false
    }

    override fun size(): Int {
        return count
    }

    override fun keyValuePairs(): List<Pair<K, V>> {
        val result = mutableListOf<Pair<K, V>>()
        for (linkedList in bucketList){
            for (pair in linkedList){
                result.add(pair)
            }
        }
        return result.toList()
    }

    override fun keys(): List<K> {
        val result = mutableListOf<K>()
        for (linkedList in bucketList){
            for (pair in linkedList){
                result.add(pair.first)
            }
        }
        return result.toList()
    }

    override fun hash(k: K): Int {
        // Extra safe logic to avoid issues with negative hashes
        return (k.hashCode() % prime + prime) % prime
    }
}

fun markovTextGeneration(filePath: String, hashOne: Int, hashTwo: Int, length: Int): String {

    val map = MyAssociativeArray<String, MyAssociativeArray<String, Int>>(hashOne)

    // AI WROTE THIS LOGIC - I had it generate a few different ways of unpacking all the words of a file,
    // and this method loads the entire file into memory all at once, which is a clean implementation for this file
    // (it is only 410 kB)
    val file = File(filePath)

    val words = file.readText()
        .lowercase()
        .split(Regex("\\W+")) // split on any non-word characters (punctuation, whitespace, etc.)
        .filter { it.isNotBlank() }

    // Code here on out is written by me
    for (i in 0..<(words.size - 1)){
        val current = words[i]
        val next = words[i+1]
        var nextWords = map[current]
        if (nextWords == null) {
            nextWords = MyAssociativeArray(hashTwo)
            map[current] = nextWords
        }
        val count = nextWords[next] ?: 0
        nextWords[next] = count + 1
    }

    // function for generating random word (for starting markov, and in case
    // current word is not followed up by another word in text.
    fun randomWord(words: List<String>): String{
        val index = Random.nextInt(0, words.size)
        return words[index]
    }
    // function for generating a random next word given current word.
    fun nextWord(current: String): String{
        var result = ""
        if (!map.contains(current)){
            return randomWord(map.keys())
        }
        val pairs = map[current]?.keyValuePairs()
        var count = 0
        if (pairs != null) {
            for (pair in pairs)
                count += pair.second
        }
        var rand = Random.nextInt(0, count)
        while (rand > 0){
            if (pairs != null){
                for (pair in pairs){
                    rand -= pair.second
                    if (rand <= 0){
                        result = pair.first
                    }
                }
            }
        }
        return result

    }

    var result = ""
    var current = randomWord(map.keys())
    for (i in 0..<length){
        result += "$current "
        current = nextWord(current)
    }
    return result
}

fun main() {
    val hashes = listOf(2, 4, 8, 16, 32, 53, 64, 97, 128, 193, 256, 389, 512, 769, 1024, 1543)
    val runTimes = mutableListOf<Double>()

    for (primeOne in hashes) {
        val runTime = measureTime {
            markovTextGeneration(
                "/home/veskew/Desktop/Olin/DSA/Assignment7/src/main/kotlin/Walks and talks of an American farmer in England.txt",
                primeOne,
                193,
                100
            )
        }
        runTimes.add(runTime.toDouble(DurationUnit.SECONDS))
    }

    println("Runtimes are $runTimes")

    val result = markovTextGeneration(
        "/home/veskew/Desktop/Olin/DSA/Assignment7/src/main/kotlin/Walks and talks of an American farmer in England.txt",
        193, 193, 100
    )
    println(result)
}
