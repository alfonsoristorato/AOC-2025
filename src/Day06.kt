fun main() {

    val input = readInput("Day06")
    val numbersSize = input.size - 1
    val operationsList = input[numbersSize].filter { !it.isWhitespace() }.toList()

    val numberLines = input.take(numbersSize)
    val rows = numberLines.map { line ->
        line.trim().split("\\s+".toRegex()).map { it.toLong() }
    }

    val columns = (rows.first().indices).map { colIndex ->
        rows.map { row -> row[colIndex] }
    }
    require(
        operationsList.size == columns.size
    )
    var totalOfOps = 0L
        operationsList.forEachIndexed { index, ch ->
        when (ch) {
            '*' -> {
                totalOfOps += columns[index].reduce { acc, i -> acc * i }
            }

            '+' -> {
                totalOfOps += columns[index].sum()
            }
        }
    }
    println("Part 1: $totalOfOps")
}
