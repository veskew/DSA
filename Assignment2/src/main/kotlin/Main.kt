package org.example



data class Node<T>(
    /**
     * data class for values within linked list
     */
    var data: T,
    var next: Node<T>? = null,
    var prev: Node<T>? = null
)

class MyLinkedList<T> {
    var head: Node<T>? = null
    var tail: Node<T>? = null

    fun pushFront(value: T) {
        when (isEmpty()) {
            true -> {
                head = Node<T>(value)
                tail = Node<T>(value)
            }
            false -> {
                val newHead: Node<T> = Node<T>(value)
                newHead.next = head
                head?.prev = newHead
                head = newHead
            }
        }
    }
    fun pushBack(value: T) {
        when (isEmpty()) {
            true -> {
                head = Node<T>(value)
                tail = Node<T>(value)
            }
            false -> {
                val newTail: Node<T> = Node<T>(value)
                newTail.prev = tail
                tail?.next = newTail
                tail = newTail
            }
        }
    }
    fun popFront(): T? {
        when (isEmpty()) {
            true -> return null
            false -> {
                val result: T? = head?.data
                head = head?.next
                head?.prev = null
                return result
            }
        }
    }
    fun popBack(): T? {
        when (isEmpty()) {
            true -> return null
            false -> {
                val result: T? = tail?.data
                tail = tail?.prev
                tail?.next = null
                return result
            }
        }
    }
    fun peekFront(): T? {
        return head?.data
    }
    fun peekBack(): T? {
        return tail?.data
    }
    fun isEmpty(): Boolean {
        return head == null && tail == null
    }
}

interface LinkedList<T> {

    /**
     * Adds the element [data] to the front of the linked list.
     */
    fun pushFront(data: T)

    /**
     * Adds the element [data] to the back of the linked list.
     */
    fun pushBack(data: T)

    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T?

    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T?

    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T?

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T?

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean
}



fun main() {
    val linkl: MyLinkedList<Int> = MyLinkedList()
    linkl.pushFront(1)
    println(linkl.peekFront())
    linkl.pushFront(1)
    println(linkl.peekFront())
    linkl.pushFront(2)
    println(linkl.peekFront())
    println(linkl.popFront())
    println(linkl.peekFront())
}