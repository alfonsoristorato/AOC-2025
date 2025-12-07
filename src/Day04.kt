fun main() {

    val mapOfRolls = readInput("Day04")
    val okRolls = buildList {
        mapOfRolls.forEachIndexed { lineIndex, toiletRollLine ->
            toiletRollLine.forEachIndexed { rowIndex, row ->
                if (row == '@') {
                    val adjacentCount = adjacentRolls(mapOfRolls, rowIndex, lineIndex)
                    if (adjacentCount < 4) {
                        add(Pair(rowIndex, lineIndex))
                    }
                }
            }
        }
    }
    println("Part 1: ${okRolls.size}")
}

private fun adjacentRolls(mapOfRolls: List<String>, currentRollX: Int, currentRollY: Int): Int {
    var adjacentCount = 0

    if (checkRollInPosition(mapOfRolls, currentRollX - 1, currentRollY)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX - 1, currentRollY - 1)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX + 1, currentRollY)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX + 1, currentRollY + 1)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX, currentRollY - 1)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX - 1, currentRollY - 1)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX, currentRollY + 1)) adjacentCount++
    if (checkRollInPosition(mapOfRolls, currentRollX + 1, currentRollY + 1)) adjacentCount++

    return adjacentCount
}

fun checkRollInPosition(mapOfRolls: List<String>, x: Int, y: Int): Boolean {
    if (y in mapOfRolls.indices && x in mapOfRolls[y].indices) {
        return mapOfRolls[y][x] == '@'
    }
    return false
}