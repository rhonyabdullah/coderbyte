package datastructure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SinglyLinkedListTest {

    private lateinit var list: SinglyLinkedList<Int>
    private lateinit var stringList: SinglyLinkedList<String>

    @BeforeEach
    fun setUp() {
        list = SinglyLinkedList()
        stringList = SinglyLinkedList()
    }

    // Test 1-10: isEmpty() tests
    @Test
    fun test1_isEmptyOnNewList() {
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test2_isEmptyAfterAppend() {
        list.append(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test3_isEmptyAfterPrepend() {
        list.prepend(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test4_isEmptyAfterRemoveAll() {
        list.append(1)
        list.remove(1)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test5_isEmptyStringList() {
        Assertions.assertTrue(stringList.isEmpty())
    }

    @Test
    fun test6_isEmptyAfterMultipleAppends() {
        list.append(1).append(2).append(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test7_isEmptyAfterMultiplePrepends() {
        list.prepend(1).prepend(2).prepend(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test8_isEmptyAfterMixedOperations() {
        list.append(1).prepend(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test9_isEmptyAfterRemoveNonExistent() {
        list.append(1)
        list.remove(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test10_isEmptyAfterRemoveFromEmpty() {
        list.remove(1)
        Assertions.assertTrue(list.isEmpty())
    }

    // Test 11-25: append() tests
    @Test
    fun test11_appendSingleElement() {
        list.append(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test12_appendMultipleElements() {
        list.append(1).append(2).append(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test13_appendNullValue() {
        val nullableList = SinglyLinkedList<Int?>()
        nullableList.append(null)
        Assertions.assertFalse(nullableList.isEmpty())
    }

    @Test
    fun test14_appendStringElements() {
        stringList.append("hello").append("world")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test15_appendDuplicateElements() {
        list.append(1).append(1).append(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test16_appendZero() {
        list.append(0)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test17_appendNegativeNumbers() {
        list.append(-1).append(-2).append(-3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test18_appendLargeNumbers() {
        list.append(Integer.MAX_VALUE).append(Integer.MIN_VALUE)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test19_appendEmptyString() {
        stringList.append("")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test20_appendSpecialCharacters() {
        stringList.append("!@#$%").append("αβγ").append("中文")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test21_appendAfterRemove() {
        list.append(1).remove(1).append(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test22_appendChaining() {
        val result = list.append(1).append(2).append(3)
        Assertions.assertSame(list, result)
    }

    @Test
    fun test23_appendManyElements() {
        for (i in 1..100) {
            list.append(i)
        }
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test24_appendAfterPrepend() {
        list.prepend(1).append(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test25_appendSameElementMultipleTimes() {
        list.append(5).append(5).append(5).append(5).append(5)
        Assertions.assertFalse(list.isEmpty())
    }

    // Test 26-40: prepend() tests
    @Test
    fun test26_prependSingleElement() {
        list.prepend(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test27_prependMultipleElements() {
        list.prepend(1).prepend(2).prepend(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test28_prependNullValue() {
        val nullableList = SinglyLinkedList<Int?>()
        nullableList.prepend(null)
        Assertions.assertFalse(nullableList.isEmpty())
    }

    @Test
    fun test29_prependStringElements() {
        stringList.prepend("hello").prepend("world")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test30_prependDuplicateElements() {
        list.prepend(1).prepend(1).prepend(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test31_prependZero() {
        list.prepend(0)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test32_prependNegativeNumbers() {
        list.prepend(-1).prepend(-2).prepend(-3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test33_prependLargeNumbers() {
        list.prepend(Integer.MAX_VALUE).prepend(Integer.MIN_VALUE)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test34_prependEmptyString() {
        stringList.prepend("")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test35_prependSpecialCharacters() {
        stringList.prepend("!@#$%").prepend("αβγ").prepend("中文")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test36_prependAfterRemove() {
        list.append(1).remove(1).prepend(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test37_prependChaining() {
        val result = list.prepend(1).prepend(2).prepend(3)
        Assertions.assertSame(list, result)
    }

    @Test
    fun test38_prependManyElements() {
        for (i in 1..100) {
            list.prepend(i)
        }
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test39_prependAfterAppend() {
        list.append(1).prepend(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test40_prependSameElementMultipleTimes() {
        list.prepend(5).prepend(5).prepend(5).prepend(5).prepend(5)
        Assertions.assertFalse(list.isEmpty())
    }

    // Test 41-65: remove() tests
    @Test
    fun test41_removeFromEmptyList() {
        list.remove(1)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test42_removeSingleElement() {
        list.append(1).remove(1)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test43_removeNonExistentElement() {
        list.append(1).append(2).remove(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test44_removeFirstElement() {
        list.append(1).append(2).append(3).remove(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test45_removeMiddleElement() {
        list.append(1).append(2).append(3).remove(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test46_removeLastElement() {
        list.append(1).append(2).append(3).remove(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test47_removeDuplicateElements() {
        list.append(1).append(1).append(2).remove(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test48_removeAllElements() {
        list.append(1).append(2).append(3)
        list.remove(1).remove(2).remove(3)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test49_removeNullValue() {
        val nullableList = SinglyLinkedList<Int?>()
        nullableList.append(null).append(1).remove(null)
        Assertions.assertFalse(nullableList.isEmpty())
    }

    @Test
    fun test50_removeStringElement() {
        stringList.append("hello").append("world").remove("hello")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test51_removeZero() {
        list.append(0).append(1).remove(0)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test52_removeNegativeNumber() {
        list.append(-1).append(1).remove(-1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test53_removeLargeNumber() {
        list.append(Integer.MAX_VALUE).append(1).remove(Integer.MAX_VALUE)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test54_removeEmptyString() {
        stringList.append("").append("hello").remove("")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test55_removeSpecialCharacters() {
        stringList.append("!@#$%").append("hello").remove("!@#$%")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test56_removeChaining() {
        val result = list.append(1).append(2).append(3).remove(2)
        Assertions.assertSame(list, result)
    }

    @Test
    fun test57_removeFromSingleElementList() {
        list.append(1).remove(1)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test58_removeMultipleSameElements() {
        list.append(1).append(1).append(1).remove(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test59_removeAfterPrepend() {
        list.prepend(1).prepend(2).remove(1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test60_removeHeadElement() {
        list.prepend(1).prepend(2).remove(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test61_removeFromLargeList() {
        for (i in 1..100) {
            list.append(i)
        }
        list.remove(50)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test62_removeNonExistentFromLargeList() {
        for (i in 1..100) {
            list.append(i)
        }
        list.remove(101)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test63_removeAllFromLargeList() {
        for (i in 1..10) {
            list.append(i)
        }
        for (i in 1..10) {
            list.remove(i)
        }
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test64_removeAfterMixedOperations() {
        list.append(1).prepend(2).append(3).remove(2)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test65_removeMultipleTimesFromEmpty() {
        list.remove(1).remove(2).remove(3)
        Assertions.assertTrue(list.isEmpty())
    }

    // Test 66-80: printList() tests
    @Test
    fun test66_printEmptyList() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.endsWith("null"))
        System.setOut(System.out)
    }

    @Test
    fun test67_printSingleElement() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(1).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("1 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test68_printMultipleElements() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(1).append(2).append(3).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("1 -> 2 -> 3 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test69_printAfterPrepend() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.prepend(1).prepend(2).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("2 -> 1 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test70_printAfterRemove() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(1).append(2).append(3).remove(2).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("1 -> 3 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test71_printStringList() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        stringList.append("hello").append("world").printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("hello -> world -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test72_printNullValues() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        val nullableList = SinglyLinkedList<Int?>()
        nullableList.append(null).append(1).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("null -> 1 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test73_printChaining() {
        val result = list.append(1).printList()
        Assertions.assertSame(list, result)
    }

    @Test
    fun test74_printZero() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(0).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("0 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test75_printNegativeNumbers() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(-1).append(-2).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("-1 -> -2 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test76_printLargeNumbers() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(Integer.MAX_VALUE).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("${Integer.MAX_VALUE} -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test77_printEmptyString() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        stringList.append("").printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.endsWith("-> null"))
        System.setOut(System.out)
    }

    @Test
    fun test78_printSpecialCharacters() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        stringList.append("!@#$%").printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("!@#$% -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test79_printDuplicateElements() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(1).append(1).append(1).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("1 -> 1 -> 1 -> null"))
        System.setOut(System.out)
    }

    @Test
    fun test80_printAfterMixedOperations() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        list.append(1).prepend(2).append(3).remove(1).printList()
        val output = outputStream.toString().trim()
        Assertions.assertTrue(output.contains("2 -> 3 -> null"))
        System.setOut(System.out)
    }

    // Test 81-100: Complex integration tests
    @Test
    fun test81_complexOperationSequence1() {
        list.append(1).append(2).prepend(0).remove(1).append(3)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test82_complexOperationSequence2() {
        list.prepend(5).prepend(4).append(6).remove(4).remove(5).remove(6)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test83_alternatingAppendPrepend() {
        for (i in 1..10) {
            if (i % 2 == 0) list.append(i) else list.prepend(i)
        }
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test84_buildAndDestroyList() {
        for (i in 1..10) {
            list.append(i)
        }
        for (i in 1..10) {
            list.remove(i)
        }
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test85_removeNonExistentFromComplexList() {
        list.append(1).prepend(2).append(3).prepend(4)
        list.remove(5)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test86_multipleRemoveSameElement() {
        list.append(1).append(1).append(1)
        list.remove(1).remove(1).remove(1)
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test87_largeScaleOperations() {
        for (i in 1..50) {
            list.append(i)
        }
        for (i in 1..25) {
            list.remove(i)
        }
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test88_stringComplexOperations() {
        stringList.append("a").prepend("b").append("c").remove("b")
        Assertions.assertFalse(stringList.isEmpty())
    }

    @Test
    fun test89_nullableComplexOperations() {
        val nullableList = SinglyLinkedList<Int?>()
        nullableList.append(null).append(1).prepend(null).remove(null)
        Assertions.assertFalse(nullableList.isEmpty())
    }

    @Test
    fun test90_chainingAllOperations() {
        val result = list.append(1).prepend(2).remove(1).append(3).printList()
        Assertions.assertSame(list, result)
    }

    @Test
    fun test91_edgeCaseZeroOperations() {
        list.append(0).prepend(0).remove(0)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test92_edgeCaseNegativeOperations() {
        list.append(-1).prepend(-2).remove(-1)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test93_edgeCaseMaxIntOperations() {
        list.append(Integer.MAX_VALUE).prepend(Integer.MIN_VALUE).remove(Integer.MAX_VALUE)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test94_repeatedOperationsOnEmpty() {
        for (i in 1..10) {
            list.remove(i)
        }
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test95_buildRemoveRebuild() {
        list.append(1).append(2).remove(1).remove(2)
        list.append(3).append(4)
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test96_stressTestAppend() {
        for (i in 1..1000) {
            list.append(i)
        }
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test97_stressTestPrepend() {
        for (i in 1..1000) {
            list.prepend(i)
        }
        Assertions.assertFalse(list.isEmpty())
    }

    @Test
    fun test98_stressTestRemove() {
        for (i in 1..100) {
            list.append(i)
        }
        for (i in 1..100) {
            list.remove(i)
        }
        Assertions.assertTrue(list.isEmpty())
    }

    @Test
    fun test99_mixedTypeOperations() {
        val doubleList = SinglyLinkedList<Double>()
        doubleList.append(1.5).prepend(2.5).remove(1.5)
        Assertions.assertFalse(doubleList.isEmpty())
    }

    @Test
    fun test100_finalIntegrationTest() {
        // Complex scenario combining all operations
        list.append(1).append(2).prepend(0).remove(1)
        list.append(3).prepend(-1).remove(0).remove(2)
        list.append(4).remove(3).remove(-1).remove(4)
        Assertions.assertTrue(list.isEmpty())
    }
}