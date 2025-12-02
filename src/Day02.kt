fun main() {

    val idRanges = readInput("Day02")
        .first()
        .split(",")
        .map {
            it.split("-")
        }
        .map {
            Pair(it[0].toLong(), it[1].toLong())
        }
    val invalidIds =
        buildList {
            idRanges.forEach { (from, to) ->
                for (id in from..to) {
                    if (isIdInvalid(id)) {
                        add(id)
                    }
                }

            }
        }
    println("Part 1: ${invalidIds.sum()}")

}

private fun isIdInvalid(id: Long): Boolean {
    val idStr = id.toString()
    val firstHalf = idStr.take(idStr.length / 2)
    val secondHalf = idStr.substring(idStr.length / 2)
    return firstHalf == secondHalf
}