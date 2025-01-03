import kotlin.math.ceil

fun main() {
    fun part1(input: List<String>): Int {
        var line: String =""
        val set = hashSetOf<String>()
        var result = 0
        outer@ for (line in input) {
            if (line.matches(Regex("""\d+\|\d+"""))) set.add(line)
            else if (line.isNotBlank()) {
                val numbers = line.split(",")
                for (j in numbers.indices) {
                    for (k in j+1..numbers.lastIndex) {
                        if (!set.contains(numbers[j]+"|"+numbers[k])) {
                            continue@outer
                        }
                    }
                }
                result += numbers[numbers.size/2].toInt()
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var line: String =""
        val set = hashSetOf<String>()
        var result = 0
        for (line in input) {
            if (line.matches(Regex("""\d+\|\d+"""))) set.add(line)
            else if (line.isNotBlank()) {
                var valid = true
                val numbers = line.split(",")
                val count = Array<Int>(numbers.size) { 0 }
                for (j in numbers.indices) {
                    for (k in numbers.indices) {
                        if (!set.contains(numbers[j] + "|" + numbers[k])) {
                            if (k > j) valid = false
                            count[j]++
                        }
                    }
                }
                if (valid) continue
                val sortedIndices = count.withIndex().sortedBy { it.value }.map { it.index }
                result += numbers[sortedIndices[sortedIndices.size / 2]].toInt()
            }
        }
        return result
    }

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
