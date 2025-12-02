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
    solve(
        idRanges = idRanges,
        invalidIdChecker = ::isIdInvalid,
        printLnIntro = "Part 1:"
    )
    solve(
        idRanges = idRanges,
        invalidIdChecker = ::isIdInvalid2,
        printLnIntro = "Part 2:"
    )

}

private fun solve(
    idRanges:  List<Pair<Long, Long>>,
    invalidIdChecker: (Long) -> Boolean,
    printLnIntro:String
) {
    val invalidIds =
        buildList {
            idRanges.forEach { (from, to) ->
                for (id in from..to) {
                    if (invalidIdChecker(id)) {
                        add(id)
                    }
                }

            }
        }
    println("$printLnIntro ${invalidIds.sum()}")
}

private fun isIdInvalid(id: Long): Boolean {
    val idStr = id.toString()
    val firstHalf = idStr.take(idStr.length / 2)
    val secondHalf = idStr.substring(idStr.length / 2)
    return firstHalf == secondHalf
}

private fun isIdInvalid2(id: Long): Boolean {
    val idStr = id.toString()
    val idStrLength = idStr.length

    for (patternLength in 1..idStrLength / 2) {
        // patternLength must divide the total length evenly, else skip
        if (idStrLength % patternLength == 0) {
            val pattern = idStr.take(patternLength)
            val repeated = pattern.repeat(idStrLength / patternLength)
            if (repeated == idStr) {
                return true
            }
        }
    }
    return false
}