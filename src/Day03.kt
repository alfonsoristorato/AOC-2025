fun main() {
    val higherJoltages = mutableListOf<Int>()
    readInput("Day03").forEach { bank->
        var currentHigherJoltage = 0
        bank.forEachIndexed { index, battery ->
            for(i in index+1 until bank.length){
                val joltage = (battery+bank.elementAt(i).toString()).toInt()
                if(joltage > currentHigherJoltage){
                    currentHigherJoltage = joltage
                }
            }

        }
        higherJoltages.add(currentHigherJoltage)
    }
    println("Part 1: ${higherJoltages.sum()}")
}