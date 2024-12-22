import kotlin.math.abs

fun main() {
    fun validateReportDifferences(differences: List<Int>): Boolean {
        if (differences.isEmpty()) return true
        val isNegative = differences.all { it < 0 }
        val isPositive = differences.all { it > 0 }
        val isInValidRange = differences.all { it in -3..3 }
        return (isNegative || isPositive) && isInValidRange
    }

    fun part1(input: List<String>): Int {
        val reports: List<List<Int>> = input.map { line: String ->
            line.split(" ").map { it.toInt() }
        }
        var result: Int = 0
        for (instance in reports)
            if (instance.size == 1 || validateReportDifferences(instance.zipWithNext { a, b -> b - a }))
                result++
        return result
    }

    fun part2(input: List<String>): Int {
        val reports: List<List<Int>> = input.map { line: String ->
            line.split(" ").map { it.toInt() }
        }
        var result: Int = 0
        for (report in reports) {
            if (report.size <= 2) {
                result++
                continue
            }
            for (index in report.indices) {
                val newReport = report.toMutableList().apply { removeAt(index) }
                if (validateReportDifferences(newReport.zipWithNext { a, b -> b - a })) {
                    result += 1
                    break
                }
            }
        }
        return result
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
