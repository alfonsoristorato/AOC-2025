fun main() {

    val mapOfRolls = readInput("Day04")
    val okRolls = checkUntilNoMoreRemovals(mapOfRolls, emptySet(),true)
    println("Part 1: ${okRolls.size}")
    println("Part 2: ${checkUntilNoMoreRemovals(mapOfRolls, okRolls.toSet()).size}")
}

private fun checkUntilNoMoreRemovals(
    mapOfRolls: List<String>,
    removedRollsSoFar: Set<Pair<Int, Int>>,
    stopAtFirstIteration: Boolean = false
): Set<Pair<Int, Int>> {
    val removedRolls = removedRollsSoFar.toMutableSet()
    val removedRollsBefore = removedRolls.size
    mapOfRolls.forEachIndexed { lineIndex, toiletRollLine ->
        toiletRollLine.forEachIndexed { rowIndex, row ->
            if (row == '@') {
                val adjacentCount = adjacentRolls(mapOfRolls, rowIndex, lineIndex, removedRollsSoFar)
                if (adjacentCount < 4) {
                    removedRolls.add(Pair(rowIndex, lineIndex))
                }
            }
        }
    }
    if (removedRolls.size == removedRollsBefore || stopAtFirstIteration) {
        return removedRolls
    }
    return checkUntilNoMoreRemovals(mapOfRolls, removedRolls)

}

private fun adjacentRolls(
    mapOfRolls: List<String>,
    currentRollX: Int,
    currentRollY: Int,
    removedRolls: Set<Pair<Int, Int>>? = null
): Int {
    var adjacentCount = 0

    // LEFT
    if (checkRollInPosition(mapOfRolls, currentRollX - 1, currentRollY, removedRolls)) adjacentCount++
    // TOP-LEFT
    if (checkRollInPosition(mapOfRolls, currentRollX - 1, currentRollY - 1, removedRolls)) adjacentCount++
    // TOP
    if (checkRollInPosition(mapOfRolls, currentRollX, currentRollY - 1, removedRolls)) adjacentCount++
    // TOP-RIGHT
    if (checkRollInPosition(mapOfRolls, currentRollX + 1, currentRollY - 1, removedRolls)) adjacentCount++
    // RIGHT
    if (checkRollInPosition(mapOfRolls, currentRollX + 1, currentRollY, removedRolls)) adjacentCount++
    // BOTTOM-RIGHT
    if (checkRollInPosition(mapOfRolls, currentRollX + 1, currentRollY + 1, removedRolls)) adjacentCount++
    // BOTTOM
    if (checkRollInPosition(mapOfRolls, currentRollX, currentRollY + 1, removedRolls)) adjacentCount++
    // BOTTOM-LEFT
    if (checkRollInPosition(mapOfRolls, currentRollX - 1, currentRollY + 1, removedRolls)) adjacentCount++

    return adjacentCount
}

fun checkRollInPosition(mapOfRolls: List<String>, x: Int, y: Int, removedRolls: Set<Pair<Int, Int>>?): Boolean {
    if (y in mapOfRolls.indices && x in mapOfRolls[y].indices) {
        if (removedRolls != null) {
            return mapOfRolls[y][x] == '@' && !removedRolls.contains(Pair(x, y))
        }
        return mapOfRolls[y][x] == '@'
    }
    return false
}