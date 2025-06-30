@file:Suppress("FunctionName")

package algorithm

import kotlin.text.iterator

fun MinWindowSubstring(strArr: Array<String>): String {
    val n = strArr[0]
    val k = strArr[1]

    val targetFreq = mutableMapOf<Char, Int>()
    for (char in k) {
        targetFreq[char] = targetFreq.getOrDefault(char, 0) + 1
    }

    var left = 0
    var minLen = Int.MAX_VALUE
    var minWindow = ""
    val windowFreq = mutableMapOf<Char, Int>()
    var have = 0
    val need = targetFreq.size

    for (right in n.indices) {
        val rightChar = n[right]
        windowFreq[rightChar] = windowFreq.getOrDefault(rightChar, 0) + 1

        if (targetFreq.containsKey(rightChar) &&
            windowFreq[rightChar] == targetFreq[rightChar]) {
            have++
        }

        while (have == need) {
            val windowSize = right - left + 1
            if (windowSize < minLen) {
                minLen = windowSize
                minWindow = n.substring(left, right + 1)
            }

            val leftChar = n[left]
            windowFreq[leftChar] = windowFreq.getOrDefault(leftChar, 0) - 1
            if (targetFreq.containsKey(leftChar) &&
                windowFreq[leftChar]!! < targetFreq[leftChar]!!) {
                have--
            }

            left++
        }
    }

    return minWindow
}
