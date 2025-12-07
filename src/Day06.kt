fun main() {

    val input = readInput("Day06")
    val numbersSize = input.size - 1

    val opsWithIndices = input[numbersSize].mapIndexedNotNull { index, c ->
        if (!c.isWhitespace()) c to index else null
    }

    val operationsList = opsWithIndices.mapIndexed { i, (op, opIndex) ->
        val spaceCount = if (i + 1 < opsWithIndices.size) {
            opsWithIndices[i + 1].second - opIndex - 1
        } else {
            input.maxOf { it.length } - opIndex
        }
        op to spaceCount
    }

    val numberLines = input.take(numbersSize)
    val columns = operationsList.mapIndexed { idx, (_, spaceCount) ->
        numberLines.map { line ->
            println("$idx")
            line.substring(
                opsWithIndices[idx].second,
                idx * spaceCount + spaceCount + idx
            )
        }
    }

    require(
        operationsList.size == columns.size
    )
    var totalOfOps = 0L
    operationsList.map { it.first }.forEachIndexed { index, ch ->
        when (ch) {
            '*' -> {
                totalOfOps += columns[index].map { it.trim().toLong() }.reduce { acc, i -> acc * i }
            }

            '+' -> {
                totalOfOps += columns[index].sumOf { it.trim().toLong() }
            }
        }
    }
    println("Part 1: $totalOfOps")

    var totalOfOpsPart2 = 0L
    operationsList.map { it.first }.forEachIndexed { index, ch ->
        val transformedColumn = transformColumn(columns[index])
        when (ch) {
            '*' -> {
                totalOfOpsPart2 += transformedColumn.reduce { acc, i -> acc * i }
            }

            '+' -> {
                totalOfOpsPart2 += transformedColumn.sum()
            }
        }
    }
    println("Part 2: $totalOfOpsPart2")
}

fun transformColumn(column: List<String>): List<Long> {
    val maxLength = column.maxOfOrNull { it.length } ?: 0
    if (maxLength == 0) return emptyList()

    val newNumbers = mutableListOf<Long>()

    for (i in 0 until maxLength) {
        val newNumberString = column.map { s ->
            s.getOrNull(s.length - 1 - i) ?: ' '
        }.joinToString("").trim()

        if (newNumberString.isNotEmpty()) {
            newNumbers.add(newNumberString.replace(" ", "").toLong())
        }
    }
    return newNumbers
}
