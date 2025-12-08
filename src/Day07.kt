import java.awt.Point

fun main() {
    val input = readInput("Day07")
    println("Part 1: ${solve(input)}")
}

private fun solve(input: List<String>): Int {
    val startX = input.first().indexOf('S')
    var currentBeams = setOf(startX)
    var splitCount = 0

    for (y in 0 until input.size - 1) {
        val nextBeams = mutableSetOf<Int>()
        val nextLine = input[y + 1]

        for (x in currentBeams) {
            if (x < 0 || x >= nextLine.length) continue

            when (nextLine[x]) {
                '.' -> {
                    nextBeams.add(x)
                }
                '^' -> {
                    splitCount++
                    nextBeams.add(x - 1)
                    nextBeams.add(x + 1)
                }
            }
        }
        currentBeams = nextBeams
    }

    return splitCount
}
