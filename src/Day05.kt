import kotlin.math.ceil

fun main() {
    fun part1(input: List<String>): Int {
        var line: String =""
        var i = 0
        val set = hashSetOf<String>()
        while (input[i].isNotBlank()) {
            set.add(input[i])
            i++
        }
        i++
        var result = 0
        while (i < input.size) {
            val numbers = input[i].split(",")
            var valid = true
            for (j in numbers.indices) {
                for (k in j+1..numbers.lastIndex) {
                    if (!set.contains(numbers[j]+"|"+numbers[k])) {
                        valid = false
                        break
                    }
                }
                if (!valid) break
            }
            if (valid) result += numbers[numbers.size/2].toInt()
            i++
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var line: String =""
        var i = 0
        val set = hashSetOf<String>()
        while (input[i].isNotBlank()) {
            set.add(input[i])
            i++
        }
        i++
        var result = 0
        while (i < input.size) {
            val numbers = input[i].split(",")
            var valid = true
            for (j in numbers.indices) {
                for (k in j+1..numbers.lastIndex) {
                    if (!set.contains(numbers[j]+"|"+numbers[k])) {
                        valid = false
                        break
                    }
                }
                if (!valid) break
            }
            if (!valid) {
                val count = Array<Int>(numbers.size) { 0 }
                for (j in numbers.indices) {
                    for (k in numbers.indices) {
                        if (!set.contains(numbers[j]+"|"+numbers[k])) count[j]++
                    }
                }
                val sortedIndices = count.withIndex().sortedBy { it.value }.map { it.index }
                result += numbers[sortedIndices[sortedIndices.size/2]].toInt()
            }
            i++
        }
        return result
    }

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
