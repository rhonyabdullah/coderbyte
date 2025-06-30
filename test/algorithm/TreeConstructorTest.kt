package algorithm

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TreeConstructorTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `it should validate tree constructor correctly`(input: Array<String>, expected: Boolean) {
        val actual = treeConstructor(input)
        Assertions.assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun provideTestCases(): Stream<Arguments> = Stream.of(
            // Valid simple trees
            Arguments.of(arrayOf("(1,2)"), true), // Single parent-child
            Arguments.of(arrayOf("(1,2)", "(3,2)"), true), // Parent with 2 children
            Arguments.of(arrayOf("(1,2)", "(2,3)"), true), // Linear tree
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,4)"), true), // Linear tree 3 levels
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,4)", "(4,5)"), true), // Linear tree 4 levels

            // Valid balanced trees
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(2,4)"), true), // Complete binary tree
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(2,4)", "(5,4)"), true), // Balanced tree
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,3)", "(5,3)"), true), // Right-heavy tree
            Arguments.of(arrayOf("(2,1)", "(3,1)", "(4,2)", "(5,2)"), true), // Left-heavy tree
            Arguments.of(arrayOf("(1,3)", "(2,3)", "(3,4)", "(5,4)"), true), // Mixed structure

            // Valid complex trees
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,1)", "(5,1)", "(2,6)"), true), // Multi-level
            Arguments.of(arrayOf("(10,20)", "(30,20)", "(20,40)", "(50,40)"), true), // Large numbers
            Arguments.of(arrayOf("(100,200)", "(300,200)"), true), // Large numbers simple
            Arguments.of(arrayOf("(7,8)", "(9,8)", "(8,10)", "(11,10)"), true), // Sequential numbers
            Arguments.of(arrayOf("(5,6)", "(7,6)", "(6,8)", "(9,8)"), true), // Another valid tree

            // Valid single node cases
            Arguments.of(arrayOf("(0,1)"), true), // Zero as child
            Arguments.of(arrayOf("(1,0)"), true), // Zero as parent
            Arguments.of(arrayOf("(999,1000)"), true), // Large numbers
            Arguments.of(arrayOf("(1,999)"), true), // Large parent
            Arguments.of(arrayOf("(50,51)"), true), // Mid-range numbers

            // Invalid: Multiple parents for same child
            Arguments.of(arrayOf("(1,2)", "(1,3)"), false), // Child 1 has two parents
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(1,4)"), false), // Child 1 has two parents
            Arguments.of(arrayOf("(5,6)", "(5,7)"), false), // Child 5 has two parents
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(1,4)"), false), // Child 1 has two parents
            Arguments.of(arrayOf("(10,20)", "(10,30)"), false), // Child 10 has two parents

            // Invalid: More than 2 children per parent
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,2)"), false), // Parent 2 has 3 children
            Arguments.of(arrayOf("(1,5)", "(2,5)", "(3,5)"), false), // Parent 5 has 3 children
            Arguments.of(arrayOf("(10,20)", "(30,20)", "(40,20)"), false), // Parent 20 has 3 children
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,2)", "(5,2)"), false), // Parent 2 has 4 children
            Arguments.of(arrayOf("(7,8)", "(9,8)", "(10,8)", "(11,8)"), false), // Parent 8 has 4 children

            // Invalid: Cycles
            Arguments.of(arrayOf("(1,2)", "(2,1)"), false), // Direct cycle
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,1)"), false), // 3-node cycle
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,4)", "(4,1)"), false), // 4-node cycle
            Arguments.of(arrayOf("(5,6)", "(6,7)", "(7,5)"), false), // 3-node cycle different numbers
            Arguments.of(arrayOf("(10,20)", "(20,30)", "(30,40)", "(40,10)"), false), // 4-node cycle

            // Invalid: Multiple roots
            Arguments.of(arrayOf("(1,2)", "(3,4)"), false), // Two separate trees
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,5)"), false), // Disconnected component
            Arguments.of(arrayOf("(10,20)", "(30,40)"), false), // Two separate pairs
            Arguments.of(arrayOf("(1,2)", "(3,4)", "(5,6)"), false), // Three separate pairs
            Arguments.of(arrayOf("(7,8)", "(9,10)", "(11,12)"), false), // Multiple disconnected

            // Invalid: No root (all nodes have parents)
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,1)"), false), // All nodes in cycle
            Arguments.of(arrayOf("(5,6)", "(6,7)", "(7,8)", "(8,5)"), false), // 4-node cycle
            Arguments.of(arrayOf("(10,11)", "(11,12)", "(12,10)"), false), // 3-node cycle

            // Edge cases: Empty and minimal inputs
            Arguments.of(arrayOf<String>(), false), // Empty array - no valid tree
            Arguments.of(arrayOf(""), false), // Empty string
            Arguments.of(arrayOf("(1,1)"), false), // Self-reference

            // Valid edge cases with negative numbers
            Arguments.of(arrayOf("(-1,0)"), true), // Negative child
            Arguments.of(arrayOf("(0,-1)"), true), // Negative parent
            Arguments.of(arrayOf("(-1,-2)"), true), // Both negative
            Arguments.of(arrayOf("(-5,-6)", "(-7,-6)"), true), // Negative tree
            Arguments.of(arrayOf("(-10,-20)", "(-30,-20)", "(-20,-40)"), true), // Negative complex

            // Invalid cases with negative numbers
            Arguments.of(arrayOf("(-1,-2)", "(-1,-3)"), false), // Negative child with two parents
            Arguments.of(arrayOf("(-5,-6)", "(-7,-6)", "(-8,-6)"), false), // Negative parent with 3 children
            Arguments.of(arrayOf("(-1,-2)", "(-2,-1)"), false), // Negative cycle

            // Mixed positive and negative
            Arguments.of(arrayOf("(1,-2)"), true), // Positive child, negative parent
            Arguments.of(arrayOf("(-1,2)"), true), // Negative child, positive parent
            Arguments.of(arrayOf("(1,-2)", "(3,-2)"), true), // Mixed numbers valid tree
            Arguments.of(arrayOf("(-1,2)", "(-3,2)", "(2,4)"), true), // Mixed complex tree
            Arguments.of(arrayOf("(1,-2)", "(-3,4)", "(5,6)"), false), // Mixed disconnected

            // Large valid trees
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,1)", "(5,1)", "(2,6)", "(7,6)"), true),
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(2,4)", "(5,4)", "(4,6)", "(7,6)"), true),
            Arguments.of(arrayOf("(10,11)", "(12,11)", "(11,13)", "(14,13)", "(13,15)"), true),
            Arguments.of(arrayOf("(20,21)", "(22,21)", "(21,23)", "(24,23)", "(25,24)", "(26,24)"), true),

            // Complex invalid cases
            Arguments.of(
                arrayOf("(1,2)", "(3,2)", "(2,4)", "(5,4)", "(1,6)"),
                false
            ), // Child 1 has two parents in complex tree
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,2)", "(2,5)"), false), // Parent 2 has 3 children in complex tree
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,4)", "(4,2)"), false), // Cycle in complex tree
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,5)", "(6,5)"), false), // Two roots in complex tree

            // Boundary cases with same numbers in different positions
            Arguments.of(arrayOf("(2,1)", "(1,3)"), true), // Number appears as both child and parent
            Arguments.of(arrayOf("(3,2)", "(2,1)", "(1,4)"), true), // Chain with reused numbers
            Arguments.of(arrayOf("(5,4)", "(4,3)", "(3,2)", "(2,1)"), true), // Descending chain
            Arguments.of(arrayOf("(1,5)", "(2,4)", "(3,3)"), false), // Self-reference in complex

            // Performance edge cases
            Arguments.of(arrayOf("(1,100)", "(2,100)"), true), // Large parent number
            Arguments.of(arrayOf("(100,1)", "(101,1)"), true), // Large child numbers
            Arguments.of(arrayOf("(1000,2000)", "(3000,2000)"), true), // Very large numbers
            Arguments.of(arrayOf("(999,1000)", "(998,999)", "(997,998)"), true), // Large descending

            // Additional complex valid cases
            Arguments.of(arrayOf("(8,9)", "(10,9)", "(9,11)", "(12,11)"), true),
            Arguments.of(arrayOf("(15,16)", "(17,16)", "(16,18)", "(19,18)"), true),
            Arguments.of(arrayOf("(25,26)", "(27,26)", "(26,28)", "(29,28)", "(28,30)"), true),
            Arguments.of(arrayOf("(35,36)", "(37,36)", "(36,38)", "(39,38)", "(40,39)", "(41,39)"), true),

            // Additional complex invalid cases
            Arguments.of(arrayOf("(8,9)", "(8,10)"), false), // Duplicate child
            Arguments.of(arrayOf("(15,16)", "(17,16)", "(18,16)"), false), // Too many children
            Arguments.of(arrayOf("(25,26)", "(26,27)", "(27,25)"), false), // Cycle
            Arguments.of(arrayOf("(35,36)", "(37,38)"), false), // Disconnected

            // Final edge cases
            Arguments.of(arrayOf("(0,0)"), false), // Self-loop with zero
            Arguments.of(arrayOf("(42,42)"), false), // Self-loop with positive
            Arguments.of(arrayOf("(-5,-5)"), false), // Self-loop with negative
            Arguments.of(arrayOf("(1,2)", "(2,2)"), false), // Self-loop in tree
            Arguments.of(arrayOf("(1,2)", "(3,3)"), false), // Self-loop with disconnected

            // Additional test cases to reach 100 total
            Arguments.of(arrayOf("(6,7)", "(8,7)", "(7,9)", "(10,9)", "(9,11)", "(12,11)"), true), // Deep balanced tree
            Arguments.of(
                arrayOf("(100,101)", "(102,101)", "(101,103)", "(104,103)", "(103,105)"),
                true
            ), // Large numbers deep tree
            Arguments.of(arrayOf("(1,2)", "(3,4)", "(5,6)", "(7,8)"), false), // Multiple disconnected pairs
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(4,5)", "(5,6)"), false), // Two separate linear trees
            Arguments.of(
                arrayOf("(10,11)", "(12,11)", "(13,12)", "(14,12)", "(15,12)"),
                false
            ), // Parent 12 has 3 children
            Arguments.of(arrayOf("(20,21)", "(21,22)", "(22,23)", "(23,24)", "(24,25)"), true), // Long linear chain
            Arguments.of(arrayOf("(-10,-11)", "(-12,-11)", "(-11,-13)", "(-14,-13)"), true), // Negative balanced tree
            Arguments.of(
                arrayOf("(50,60)", "(70,60)", "(60,80)", "(90,80)", "(80,100)", "(110,100)"),
                true
            ), // Large numbers balanced
            Arguments.of(arrayOf("(1,2)", "(3,2)", "(4,3)", "(5,3)", "(6,4)", "(7,4)"), true), // Complex valid tree
            Arguments.of(
                arrayOf("(200,300)", "(400,300)", "(300,500)", "(600,500)", "(500,700)"),
                true
            ), // Very large numbers
            Arguments.of(arrayOf("(1,2)", "(2,3)", "(3,4)", "(4,5)", "(5,6)", "(6,1)"), false), // Long cycle
            Arguments.of(
                arrayOf("(15,16)", "(17,16)", "(18,17)", "(19,17)", "(20,18)", "(21,18)"),
                true
            ) // Final complex valid tree
        )
    }
}
