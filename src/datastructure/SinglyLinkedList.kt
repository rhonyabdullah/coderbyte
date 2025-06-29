package datastructure

class SinglyLinkedList<T> {

    private data class Node<T>(val value: T, var next: Node<T>? = null)

    private var size: Int = 0
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun isEmpty(): Boolean = size == 0

    /**
     * Tail (end of a list)
     *
     * For the
     * ```kotlin
     * else {
     *  tail?.next = newNode
     *  tail = newNode
     * }
     * ```
     *
     * # Why are both lines needed?
     * Let's walk through an example. Imagine your list currently looks like this:
     * `[Node A] -> [Node B] -> null`
     * - `head` points to `Node A`.
     * - points to `Node B`. `tail`
     *
     * Now, let's say we want to append a (let's call it `Node C`). `newNode`
     * 1. **`tail?.next = newNode`** is executed.
     *     - This sets `Node B`'s pointer to `Node C`. `next`
     *     - The list now looks like this: `[Node A] -> [Node B] -> [Node C] -> null`.
     *     - However, **still points to `Node B`**. `tail`
     *
     * 2. **`tail = newNode`** is executed.
     *     - This updates the pointer to point to `Node C`. `tail`
     *     - Now, correctly points to the new last element of the list. `tail`
     *
     * If we were to skip the second step (), our variable would be incorrect. The next time we tried to append another node, it would be added after `Node B`, breaking the list's structure. `tail = newNode``tail`
     *
     */
    fun append(value: T) = apply {
        val newNode = Node(value)
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        size++
    }

    /**
     * Head (beginning of a list)
     */
    fun prepend(value: T) = apply {
        val newNode = Node(value, head)
        head = newNode
        if (tail == null) {
            tail = newNode
        }
        size++
    }

    fun remove(value: T) = apply {
        if (isEmpty()) return@apply

        // If the head is the node to be removed.
        if (head?.value == value) {
            head = head?.next
            if (head == null) {
                // If the list becomes empty, also nullify the tail.
                tail = null
            }
            size--
            return@apply
        }

        // Search for the node to remove by finding its predecessor.
        var current = head
        while (current?.next != null && current.next?.value != value) {
            current = current.next
        }

        // If the node was found (its predecessor is 'current').
        if (current?.next != null) {
            val nodeToRemove = current.next
            // Unlink the node.
            current.next = nodeToRemove?.next
            // If we removed the tail, update the tail reference.
            if (nodeToRemove == tail) {
                tail = current
            }
            size--
        }
    }

    fun printList() = apply {
        var current = head
        val elements = mutableListOf<T>()
        while (current != null) {
            elements.add(current.value)
            current = current.next
        }
        println(elements.joinToString(" -> ") + " -> null")
    }
}
