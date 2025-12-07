fun main() {

    val input = readInput("Day05")
    val freshIdsRanges = input.takeWhile { it.isNotEmpty() }.map { idRangesString ->
        val (from, to) = idRangesString.split("-").map { it.toLong() }
        from.rangeUntil(to +1)
    }
    val ingredients = input.drop(freshIdsRanges.size + 1).map { it.toLong() }
    buildList {
        ingredients.forEach { ingredientId ->
            if(freshIdsRanges.any { it.contains(ingredientId) }){
                add(ingredientId)
            }
        }
    }.let {
        println("Part 1: ${it.size}")
    }
}
