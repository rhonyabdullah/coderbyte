package algorithm

fun treeConstructor(strArr: Array<String>): Boolean {
    // Handle empty array or array with empty strings
    if (strArr.isEmpty() || strArr.any { it.isBlank() }) return false

    val pairs = strArr.map {
        val (child, parent) = it.removeSurrounding("(", ")")
            .split(",")
            .map { str -> str.toInt() }
        child to parent
    }
    val childToParent = mutableMapOf<Child, Parent>() //each child only has 1 parent
    val parentToChildren = mutableMapOf<Parent, MutableList<Child>>()
    val allNodes = mutableSetOf<Int>()

    // Build relationships
    for ((c, p) in pairs) {
        val child = Child(c)
        val parent = Parent(p)

        // Each child should have only one parent
        if (childToParent.containsKey(child)) return false //chile already has parent, early return
        childToParent[child] = parent

        // Each parent can have at most 2 children
        val children = parentToChildren.getOrPut(parent) { mutableListOf() }
        children.add(child)
        if (children.size > 2) return false

        allNodes.add(child.value)
        allNodes.add(parent.value)
    }

    println("allNodes: ${allNodes.joinToString(",")}") //allNodes: 1,2,3,4

    //if child only has one parent it will represent the same as input
    // {Child(value=1)=Parent(value=2), Child(value=2)=Parent(value=3), Child(value=3)=Parent(value=4)}
    println("childToParent: $childToParent ")

    // Find root candidates (nodes with no parent)
    val roots = allNodes.filter { nodeValue ->
        println(
            "nodeValue: $nodeValue, " +
                    "childToParent.containsKey($nodeValue): ${childToParent.containsKey(Child(nodeValue))}"
        )
        !childToParent.containsKey(Child(nodeValue))
    }
    if (roots.size != 1) return false
    val root = Parent(roots.first())

    // Cycle detection using DFS
    fun hasCycle(node: Parent, visited: MutableSet<Int>): Boolean {
        if (!parentToChildren.containsKey(node)) return false
        for (child in parentToChildren[node]!!) {
            if (child.value in visited) return true // Cycle found
            visited.add(child.value)
            if (hasCycle(Parent(child.value), visited)) return true
            visited.remove(child.value)
        }
        return false
    }

    // Start DFS from root to check for cycles and connectivity
    val visited = mutableSetOf(root.value)
    if (hasCycle(root, visited)) return false

    // All children should be reachable from root (i.e., connected)
    val reachable = mutableSetOf<Int>()
    fun dfs(node: Parent) {
        reachable.add(node.value)
        for (child in parentToChildren[node].orEmpty()) {
            if (child.value !in reachable) {
                dfs(Parent(child.value))
            }
        }
    }
    dfs(root)

    if (reachable.size != allNodes.size) return false

    return true
}

@JvmInline
value class Parent(val value: Int)

@JvmInline
value class Child(val value: Int)
