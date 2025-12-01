import kotlin.math.absoluteValue

fun main() {

    var startingPoint = 50
    var zeroTimesPart1 = 0
    var zeroTimesPart2 = 0

    readInput("Day01")
        .forEach { line ->
            val (direction, steps) = line[0] to line.substring(1).toInt()
            val currentPoint = startingPoint

            val bump = when (direction) {
                'R' -> steps
                'L' -> -steps
                else -> throw IllegalArgumentException("Invalid direction")
            }

            startingPoint = (startingPoint + bump).mod(100)

            val untilNextZero = when {
                currentPoint == 0 -> 100
                bump > 0 -> 100 - currentPoint
                else -> currentPoint
            }

            val zeroes = when {
                bump.absoluteValue >= untilNextZero -> 1 + (bump.absoluteValue - untilNextZero) / 100
                else -> 0
            }
            zeroTimesPart2 += zeroes

            if (startingPoint == 0) {
                zeroTimesPart1++
            }
        }
    println("Part 1: $zeroTimesPart1")
    println("Part 2: $zeroTimesPart2")

}