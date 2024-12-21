import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Long {
        val (left, right) = input.map { line: String ->
            line.split("""\s+""".toRegex()).let {
                it[0].toLong() to it[1].toLong()
        }}.unzip()
        return left.sorted().zip(right.sorted()).sumOf { (a, b) -> abs(a - b) }
    }

    fun part2(input: List<String>): Long {
        val (left, right) = input.map { line: String ->
            line.split("""\s+""".toRegex()).let {
                it[0].toLong() to it[1].toLong()
        }}.unzip()
        val frequencies: Map<Long, Long> = right.groupBy { it }.mapValues { (_, values) -> values.size.toLong() }
        return left.sumOf { it * frequencies.getOrDefault(it, 0)}
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
