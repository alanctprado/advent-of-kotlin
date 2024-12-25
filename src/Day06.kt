fun main() {
    val input = readInput("Day06")
    val charMatrix = input.map { it.toMutableList() }
    val initialPosition = charMatrix.mapIndexed { i, row -> i to row.indexOfFirst { it == '^' } }.filter { it.second != -1 }[0]
    var direction = 0
    var currentX = 0
    val n = charMatrix[0].size
    var currentY = 0
    val m = charMatrix.size

    fun isOutside() = currentY < 0 || currentX < 0 || currentY >= m || currentX >= n

    fun move() {
        when (direction) {
            0 -> currentY--  // move up
            1 -> currentX++  // move right
            2 -> currentY++  // move down
            3 -> currentX--  // move left
        }
        if (isOutside()) return
        if (charMatrix[currentY][currentX] == '#') {
            // backtrack
            when (direction) {
                0 -> currentY++
                1 -> currentX--
                2 -> currentY--
                3 -> currentX++
            }
            direction = (direction + 1) % 4  // turn
            move()  // try again
        }
    }

    fun part1(): Int {
        val visitedMatrix = charMatrix.map { it.toMutableList() }
        currentX = initialPosition.second
        currentY = initialPosition.first
        while (!isOutside()) {
            visitedMatrix[currentY][currentX] = 'X'
            move()
        }
        return visitedMatrix.sumOf { row -> row.count { position -> position == 'X' } }
    }

    fun part3(): Int {
        val obstacleMatrix = charMatrix.map { it.toMutableList() }
        currentX = initialPosition.second
        currentY = initialPosition.first
        direction = 0
        while (true) {
            val previousPosition = (currentY to currentX) to direction
            move()
            if (isOutside()) break
            val obstaclePosition = currentY to currentX
            if (charMatrix[obstaclePosition.first][obstaclePosition.second] != '.') continue
            charMatrix[obstaclePosition.first][obstaclePosition.second] = '#'
            currentX = previousPosition.first.second
            currentY = previousPosition.first.first
            direction = previousPosition.second
            val visitedSet = mutableSetOf<Pair<Pair<Int, Int>, Int>>()
            while (!isOutside()) {
                visitedSet.add((currentY to currentX) to direction)
                move()
                if (isOutside()) break
                if (visitedSet.contains((currentY to currentX) to direction)) {
                    obstacleMatrix[obstaclePosition.first][obstaclePosition.second] = 'X'
                    break
                }
            }
            charMatrix[obstaclePosition.first][obstaclePosition.second] = '.'
            currentX = previousPosition.first.second
            currentY = previousPosition.first.first
            direction = previousPosition.second
            move()
        }
        val result = obstacleMatrix.sumOf { row -> row.count { position -> position == 'X' } }
        if (obstacleMatrix[initialPosition.first][initialPosition.second] == 'X') return result - 1
        return result
    }

    fun part2(): Int {
        var result = 0
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (charMatrix[i][j] == '.') {
                    charMatrix[i][j] = '#'
                    currentX = initialPosition.second
                    currentY = initialPosition.first
                    direction = 0
                    val visitedSet = mutableSetOf<Pair<Pair<Int, Int>, Int>>()
                    while (!isOutside()) {
                        visitedSet.add((currentY to currentX) to direction)
                        move()
                        if (visitedSet.contains((currentY to currentX) to direction)) {
                            result++
                            break
                        }
                    }
                    charMatrix[i][j] = '.'
                }
            }
        }
        return result
    }

    part1().println()
    part2().println()
    part3().println()
}
