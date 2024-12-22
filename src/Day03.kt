import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val pattern = Regex("""mul\(\d{1,3},\d{1,3}\)""")
        val validInstructions = input.map {
            pattern.findAll(it).map { it.value }.toList()
        }
        return(validInstructions.sumOf { instructions ->
          instructions.sumOf { instruction ->
            instruction.replace("mul", "").replace("(", "").replace(")", "").split(",")
              .let { it[0].toInt() * it[1].toInt() }
          }
        })
    }

    fun part2(input: List<String>): Int {
        val pattern = Regex("""(do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\))""")
        val validInstructions = input.map {
            pattern.findAll(it).map { it.value }.toList()
        }.flatten()
        var valid = true
        val invalidIndices = mutableListOf<Int>()
        for (i in validInstructions.indices) {
            if (!valid) {
                invalidIndices.add(i)
                if (validInstructions[i] == "do()") valid = true
            } else if (validInstructions[i] == "don't()") {
                valid = false
                invalidIndices.add(i)
            } else if (validInstructions[i] == "do()") {
                invalidIndices.add(i)
            }
        }
        val enabledInstructions = validInstructions.filterIndexed { index, _ -> index !in invalidIndices }
        return(enabledInstructions.sumOf { instruction ->
            instruction.replace("mul", "").replace("(", "").replace(")", "").split(",")
                .let { it[0].toInt() * it[1].toInt() }
        })
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
