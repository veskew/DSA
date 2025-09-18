package org.example

data class Node<T>(
    /**
     * Helper data class for nodes within linked list
     */
    var value: T,
    // next and prev are both Nodes also, initially null.
    var next: Node<T>? = null,
    var prev: Node<T>? = null
)

class MyLinkedList<T> : LinkedList<T> {
    /**
     * Linked list implementation following interface
     */
    var head: Node<T>? = null
    var tail: Node<T>? = null

    override fun pushFront(data: T) {
        when (isEmpty()) {
            true -> {
                head = Node<T>(data)
                tail = head
            }
            false -> {
                val newHead: Node<T> = Node<T>(data)
                newHead.next = head
                head?.prev = newHead
                head = newHead
            }
        }
    }
    override fun pushBack(data: T) {
        when (isEmpty()) {
            true -> {
                head = Node<T>(data)
                tail = head
            }
            false -> {
                val newTail: Node<T> = Node<T>(data)
                newTail.prev = tail
                tail?.next = newTail
                tail = newTail
            }
        }
    }
    override fun popFront(): T? {
        val result: T? = head?.value
        if (head?.next == null || tail?.prev == null) {
            head = null
            tail = null
        }
        else if (!isEmpty()){
            head = head?.next
            head?.prev = null
        }
        return result
    }

    override fun popBack(): T? {
        val result: T? = tail?.value
        if (tail?.prev == null || head?.next == null) {
            head = null
            tail = null
        }
        else if (!isEmpty()){
            tail = tail?.prev
            tail?.next = null
        }
        return result
    }
    override fun peekFront(): T? {
        return head?.value
    }
    override fun peekBack(): T? {
        return tail?.value
    }
    override fun isEmpty(): Boolean {
        return head == null && tail == null
    }
}

class MyStack<T> : Stack<T> {
    val stackLinkedList: MyLinkedList<T> = MyLinkedList()

    override fun push(data: T) {
        stackLinkedList.pushBack(data)
    }
    override fun pop(): T? {
        return stackLinkedList.popBack()
    }
    override fun peek(): T? {
        return stackLinkedList.peekBack()
    }
    override fun isEmpty(): Boolean {
        return stackLinkedList.isEmpty()
    }



}

class MyQueue<T>: Queue<T> {
    val queueLinkedList: MyLinkedList<T> = MyLinkedList()

    override fun enqueue(data: T) {
        queueLinkedList.pushBack(data)
    }

    override fun dequeue(): T? {
        return queueLinkedList.popFront()
    }

    override fun peek(): T? {
        return queueLinkedList.peekFront()
    }

    override fun isEmpty(): Boolean {
        return queueLinkedList.isEmpty()
    }

}


fun main() {

}