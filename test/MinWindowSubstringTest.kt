import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class MinWindowSubstringTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `it should find the minimum window substring`(input: Array<String>, expected: String) {
        val actual = MinWindowSubstring(input)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun provideTestCases(): Stream<Arguments> = Stream.of(
            // Original test cases
            Arguments.of(arrayOf("aaffhkksemckelloe", "fhea"), "affhkkse"),
            Arguments.of(arrayOf("ahffaksfajeeubsne", "jefaa"), "aksfaje"),
            Arguments.of(arrayOf("aaabaaddae", "aed"), "dae"),
            Arguments.of(arrayOf("aaffsfsfasfasfasfasfasfacasfafe", "fafe"), "fafe"),
            Arguments.of(arrayOf("aaffsfsfasfasfasfasfasfacasfafe", "fafsf"), "affsf"),
            Arguments.of(arrayOf("vvavereveaevafefaef", "vvev"), "vvave"),
            Arguments.of(arrayOf("caae", "cae"), "caae"),
            Arguments.of(arrayOf("cccaabbbbrr", "rbac"), "caabbbbr"),
            Arguments.of(arrayOf("aaaaaaaaaa", "a"), "a"),

            // Edge cases - Empty and single character
            Arguments.of(arrayOf("", ""), ""),
            Arguments.of(arrayOf("", "a"), ""),
            Arguments.of(arrayOf("a", "a"), "a"),
            Arguments.of(arrayOf("b", "a"), ""),
            Arguments.of(arrayOf("ab", "a"), "a"),
            Arguments.of(arrayOf("ab", "b"), "b"),
            Arguments.of(arrayOf("ba", "a"), "a"),
            Arguments.of(arrayOf("ba", "b"), "b"),

            // No valid window cases
            Arguments.of(arrayOf("abc", "d"), ""),
            Arguments.of(arrayOf("abc", "xyz"), ""),
            Arguments.of(arrayOf("hello", "xyz"), ""),
            Arguments.of(arrayOf("programming", "xyz"), ""),
            Arguments.of(arrayOf("test", "xyz"), ""),

            // Pattern longer than string
            Arguments.of(arrayOf("a", "ab"), ""),
            Arguments.of(arrayOf("ab", "abc"), ""),
            Arguments.of(arrayOf("hello", "helloworld"), ""),
            Arguments.of(arrayOf("test", "testing"), ""),
            Arguments.of(arrayOf("code", "codebase"), ""),

            // Pattern with duplicate characters
            Arguments.of(arrayOf("aab", "aa"), "aa"),
            Arguments.of(arrayOf("aaab", "aa"), "aa"),
            Arguments.of(arrayOf("abaa", "aa"), "aa"),
            Arguments.of(arrayOf("aabaa", "aa"), "aa"),
            Arguments.of(arrayOf("aaabbbccc", "abc"), "abbbc"),
            Arguments.of(arrayOf("aaabbbccc", "aabbcc"), "aabbbcc"),
            Arguments.of(arrayOf("abcabc", "abc"), "abc"),
            Arguments.of(arrayOf("abcabc", "aabbcc"), "abcabc"),

            // String with all same characters
            Arguments.of(arrayOf("aaaa", "a"), "a"),
            Arguments.of(arrayOf("aaaa", "aa"), "aa"),
            Arguments.of(arrayOf("aaaa", "aaa"), "aaa"),
            Arguments.of(arrayOf("aaaa", "aaaa"), "aaaa"),
            Arguments.of(arrayOf("bbbb", "b"), "b"),
            Arguments.of(arrayOf("cccc", "cc"), "cc"),

            // Exact match cases
            Arguments.of(arrayOf("abc", "abc"), "abc"),
            Arguments.of(arrayOf("hello", "hello"), "hello"),
            Arguments.of(arrayOf("test", "test"), "test"),
            Arguments.of(arrayOf("programming", "programming"), "programming"),

            // Pattern is substring
            Arguments.of(arrayOf("hello", "ell"), "ell"),
            Arguments.of(arrayOf("programming", "gram"), "gram"),
            Arguments.of(arrayOf("testing", "est"), "tes"),
            Arguments.of(arrayOf("algorithm", "rit"), "rit"),

            // Multiple valid windows - should return shortest
            Arguments.of(arrayOf("abcdef", "cf"), "cdef"),
            Arguments.of(arrayOf("abcdefcf", "cf"), "fc"),
            Arguments.of(arrayOf("aabbcc", "abc"), "abbc"),
            Arguments.of(arrayOf("aabbccabc", "abc"), "cab"),
            Arguments.of(arrayOf("abcabcabc", "abc"), "abc"),

            // Complex patterns with multiple duplicates
            Arguments.of(arrayOf("aabbccddee", "ace"), "abbccdde"),
            Arguments.of(arrayOf("aabbccddee", "abcde"), "abbccdde"),
            Arguments.of(arrayOf("abcdefghijk", "ace"), "abcde"),
            Arguments.of(arrayOf("xyabcdefghijk", "ace"), "abcde"),

            // Case sensitivity tests
            Arguments.of(arrayOf("Hello", "H"), "H"),
            Arguments.of(arrayOf("Hello", "h"), ""),
            Arguments.of(arrayOf("HeLLo", "HL"), "HeL"),
            Arguments.of(arrayOf("Programming", "Pg"), "Prog"),
            Arguments.of(arrayOf("TeSt", "Te"), "Te"),

            // Special characters and numbers
            Arguments.of(arrayOf("a1b2c3", "123"), "1b2c3"),
            Arguments.of(arrayOf("!@#$%", "!%"), "!@#$%"),
            Arguments.of(arrayOf("a!b@c#", "!@#"), "!b@c#"),
            Arguments.of(arrayOf("123abc456", "a4"), "abc4"),
            Arguments.of(arrayOf("hello@world", "@w"), "@w"),

            // Whitespace handling
            Arguments.of(arrayOf("a b c", " "), " "),
            Arguments.of(arrayOf("hello world", "ow"), "wo"),
            Arguments.of(arrayOf("  test  ", "t "), " t"),
            Arguments.of(arrayOf("a b c d", "bd"), "b c d"),

            // Long strings with small patterns
            Arguments.of(arrayOf("a".repeat(1000) + "b", "b"), "b"),
            Arguments.of(arrayOf("a".repeat(500) + "bc" + "a".repeat(500), "bc"), "bc"),
            Arguments.of(arrayOf("x".repeat(100) + "yz" + "x".repeat(100), "yz"), "yz"),

            // Patterns at beginning and end
            Arguments.of(arrayOf("abcdef", "ab"), "ab"),
            Arguments.of(arrayOf("abcdef", "ef"), "ef"),
            Arguments.of(arrayOf("abcdef", "af"), "abcdef"),
            Arguments.of(arrayOf("programming", "pr"), "pr"),
            Arguments.of(arrayOf("programming", "ng"), "ng"),

            // Overlapping characters
            Arguments.of(arrayOf("ababa", "aba"), "aba"),
            Arguments.of(arrayOf("ababab", "ab"), "ab"),
            Arguments.of(arrayOf("abcabc", "ca"), "ca"),
            Arguments.of(arrayOf("xyzxyz", "yx"), "xy"),

            // Performance edge cases
            Arguments.of(arrayOf("a".repeat(100), "a"), "a"),
            Arguments.of(arrayOf("ab".repeat(50), "ab"), "ab"),
            Arguments.of(arrayOf("abc".repeat(33), "abc"), "abc"),
            Arguments.of(arrayOf("x".repeat(50) + "y" + "x".repeat(50), "y"), "y"),

            // Complex realistic scenarios
            Arguments.of(arrayOf("ADOBECODEBANC", "ABC"), "BANC"),
            Arguments.of(arrayOf("this is a test string", "tist"), "t stri"),
            Arguments.of(arrayOf("minimum window substring", "ow"), "ow"),
            Arguments.of(arrayOf("find the shortest window", "shot"), "short"),
            Arguments.of(arrayOf("algorithm analysis", "alan"), "anal"),

            // Unicode and special cases
            Arguments.of(arrayOf("αβγδε", "αγ"), "αβγ"),
            Arguments.of(arrayOf("🚀🌟⭐🎯", "🚀⭐"), "🚀🌟⭐"),
            Arguments.of(arrayOf("café", "é"), "é"),

            // Boundary value testing
            Arguments.of(arrayOf("z", "z"), "z"),
            Arguments.of(arrayOf("zz", "z"), "z"),
            Arguments.of(arrayOf("zy", "z"), "z"),
            Arguments.of(arrayOf("yz", "z"), "z"),

            // Additional complex cases
            Arguments.of(arrayOf("abcdefghijklmnop", "aei"), "abcdefghi"),
            Arguments.of(arrayOf("xyzzabc", "zab"), "zab"),
            Arguments.of(arrayOf("programming is fun", "fun"), "fun"),
            Arguments.of(arrayOf("sliding window technique", "wing"), "ing w"),
            Arguments.of(arrayOf("data structures and algorithms", "sad"), "s and"),

            // Final edge cases
            Arguments.of(arrayOf("abcdefg", "gfedcba"), "abcdefg"),
            Arguments.of(arrayOf("reverse", "esrever"), "reverse"),
            Arguments.of(arrayOf("palindrome", "emord"), "drome"),
            Arguments.of(arrayOf("racecar", "car"), "rac"),
            Arguments.of(arrayOf("level", "vel"), "lev")
        )
    }
}
