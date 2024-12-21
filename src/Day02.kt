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
        val differences: List<List<Int>> = reports.map { report: List<Int> ->
            report.zipWithNext { a, b -> b - a }
        }
        var result: Int = 0
        for (instance in differences) if (validateReportDifferences(instance)) result += 1
        return result
    }

    fun part2(input: List<String>): Int {
        val reports: List<List<Int>> = input.map { line: String ->
            line.split(" ").map { it.toInt() }
        }
        var result: Int = 0
        for (report in reports) {
            val differences: List<Int> = report.zipWithNext { a, b -> b - a }
            if (validateReportDifferences(differences)) {
                result += 1
                continue
            }
            for (index in report.indices) {
                val newReport = report.toMutableList().apply { removeAt(index) }
                val new_differences: List<Int> = newReport.zipWithNext { a, b -> b - a }
                if (validateReportDifferences(new_differences)) {
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
