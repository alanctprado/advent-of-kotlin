import kotlin.math.abs

fun main() {
    val input = readInput("Day04")
    val charMatrix = input.map { it.toList() }
    val m = charMatrix.size
    val n = charMatrix[0].size

    fun left(i: Int, j: Int) = if (j < 3) false else charMatrix[i][j] == 'X' && charMatrix[i][j-1] == 'M' && charMatrix[i][j-2] == 'A' && charMatrix[i][j-3] == 'S'
    fun right(i: Int, j: Int) = if (j > n - 4) false else charMatrix[i][j] == 'X' && charMatrix[i][j+1] == 'M' && charMatrix[i][j+2] == 'A' && charMatrix[i][j+3] == 'S'
    fun top(i: Int, j: Int) = if (i < 3) false else charMatrix[i][j] == 'X' && charMatrix[i-1][j] == 'M' && charMatrix[i-2][j] == 'A' && charMatrix[i-3][j] == 'S'
    fun bottom(i: Int, j: Int) = if (i > m - 4) false else charMatrix[i][j] == 'X' && charMatrix[i+1][j] == 'M' && charMatrix[i+2][j] == 'A' && charMatrix[i+3][j] == 'S'
    fun topLeft(i: Int, j: Int) = if (j < 3 || i < 3) false else charMatrix[i][j] == 'X' && charMatrix[i-1][j-1] == 'M' && charMatrix[i-2][j-2] == 'A' && charMatrix[i-3][j-3] == 'S'
    fun topRight(i: Int, j: Int) = if (j > n - 4 || i < 3) false else charMatrix[i][j] == 'X' && charMatrix[i-1][j+1] == 'M' && charMatrix[i-2][j+2] == 'A' && charMatrix[i-3][j+3] == 'S'
    fun bottomLeft(i: Int, j: Int) = if (j < 3 || i > m - 4) false else charMatrix[i][j] == 'X' && charMatrix[i+1][j-1] == 'M' && charMatrix[i+2][j-2] == 'A' && charMatrix[i+3][j-3] == 'S'
    fun bottomRight(i: Int, j: Int) = if (j > n - 4 || i > m - 4) false else charMatrix[i][j] == 'X' && charMatrix[i+1][j+1] == 'M' && charMatrix[i+2][j+2] == 'A' && charMatrix[i+3][j+3] == 'S'

    fun part1(): Int {
        var result = 0
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (left(i,j)) result++
                if (right(i,j)) result++
                if (top(i,j)) result++
                if (bottom(i,j)) result++
                if (topLeft(i,j)) result++
                if (topRight(i,j)) result++
                if (bottomLeft(i,j)) result++
                if (bottomRight(i,j)) result++
            }
        }
        return result
    }

    fun xmas1(i: Int, j: Int) = charMatrix[i-1][j-1] == 'M' && charMatrix[i+1][j-1] == 'M' && charMatrix[i+1][j+1] == 'S' && charMatrix[i-1][j+1] == 'S'
    fun xmas2(i: Int, j: Int) = charMatrix[i-1][j-1] == 'M' && charMatrix[i+1][j-1] == 'S' && charMatrix[i+1][j+1] == 'S' && charMatrix[i-1][j+1] == 'M'
    fun xmas3(i: Int, j: Int) = charMatrix[i-1][j-1] == 'S' && charMatrix[i+1][j-1] == 'S' && charMatrix[i+1][j+1] == 'M' && charMatrix[i-1][j+1] == 'M'
    fun xmas4(i: Int, j: Int) = charMatrix[i-1][j-1] == 'S' && charMatrix[i+1][j-1] == 'M' && charMatrix[i+1][j+1] == 'M' && charMatrix[i-1][j+1] == 'S'

    fun part2(): Int {
        var result = 0
        for (i in 1..<m-1) {
            for (j in 1..<n-1) {
                if (charMatrix[i][j] == 'A') {
                    if (xmas1(i,j) || xmas2(i,j) || xmas3(i,j) || xmas4(i,j)) result++
                }
            }
        }
        return result
    }

    part1().println()
    part2().println()
}
