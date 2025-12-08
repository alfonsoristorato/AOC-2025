import java.math.BigInteger

fun main() {
    val input = readInput("Day03")


    val part1Total = input.sumOf { bank ->
        var maxJoltage = 0
        for (i in 0 until bank.length) {
            for (j in i + 1 until bank.length) {
                val joltage = "${bank[i]}${bank[j]}".toInt()
                if (joltage > maxJoltage) {
                    maxJoltage = joltage
                }
            }
        }
        maxJoltage.toLong()
    }
    println("Part 1: $part1Total")


    val part2Total = input.fold(BigInteger.ZERO) { acc, bank ->
        acc + findLargestNumber(bank).toBigInteger()
    }
    println("Part 2: $part2Total")
}

private fun findLargestNumber(s: String, len: Int = 12): String {
    if (len == 0) return ""
    if (s.length <= len) return s

    val result = StringBuilder()
    var start = 0
    for (i in 0 until len) {
        val end = s.length - (len - i)
        var maxChar = '0'
        var maxIndex = -1

        for (j in start..end) {
            if (s[j] > maxChar) {
                maxChar = s[j]
                maxIndex = j
            }
        }
        result.append(maxChar)
        start = maxIndex + 1
    }
    return result.toString()
}
