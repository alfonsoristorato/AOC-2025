fun main() {

    val input = readInput("Day05")
    val freshIdsRanges = input.takeWhile { it.isNotEmpty() }.map { idRangesString ->
        val (from, to) = idRangesString.split("-").map { it.toLong() }
        from.rangeUntil(to + 1)
    }
    val ingredients = input.drop(freshIdsRanges.size + 1).map { it.toLong() }
    buildList {
        ingredients.forEach { ingredientId ->
            if (freshIdsRanges.any { it.contains(ingredientId) }) {
                add(ingredientId)
            }
        }
    }.let {
        println("Part 1: ${it.size}")
    }

    val sortedRanges = freshIdsRanges.sortedBy { it.first }
    val mergedRanges = mutableListOf<LongRange>()


    var currentMerge = sortedRanges.first()
    for (i in 1 until sortedRanges.size) {
        val nextRange = sortedRanges[i]

        if (nextRange.first <= currentMerge.last + 1) {

            currentMerge = currentMerge.first..maxOf(currentMerge.last, nextRange.last)
        } else {
            mergedRanges.add(currentMerge)
            currentMerge = nextRange
        }
    }
    mergedRanges.add(currentMerge)


    val totalSize = mergedRanges.sumOf { it.last - it.first + 1 }
    println("Part 2: $totalSize")
}
