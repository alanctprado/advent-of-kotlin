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

    fun resetPosition(newX: Int, newY: Int, newDirection: Int) {
        currentX = newX
        currentY = newY
        direction = newDirection
    }

    fun resetPosition(newPosition: Pair<Pair<Int, Int>, Int>) = resetPosition(newPosition.first.second, newPosition.first.first, newPosition.second)

    fun currentPosition() = (currentY to currentX) to direction

    fun part2(): Int {
        val obstacleMatrix = charMatrix.map { it.toMutableList() }
        resetPosition(initialPosition.second, initialPosition.first, 0)  // resetting after running part1
        val visitedSet = mutableSetOf<Pair<Pair<Int, Int>, Int>>()

        // note: obstacles only matter if they are found within the original path
        while (true) {
            val previousPosition= currentPosition()
            visitedSet.add(previousPosition)

            // get the next possible obstacle position and add it to the grid
            move()
            if (isOutside()) break  // cannot place an obstacle outside the grid
            val obstaclePosition = currentPosition().first
            if (obstacleMatrix[obstaclePosition.first][obstaclePosition.second] != '.') continue  // either the start position or one that's already been checked
            obstacleMatrix[obstaclePosition.first][obstaclePosition.second] = '?'  // mark places we already checked -- checking it again later in time is not valid
            charMatrix[obstaclePosition.first][obstaclePosition.second] = '#'  // add the obstacle
            resetPosition(previousPosition)  // the next move will run into the obstacle and check a new path

            // move until grid is left or loop is found
            val newVisitedSet = visitedSet.toMutableSet()
            while (!isOutside()) {
                newVisitedSet.add(currentPosition())
                move()
                if (newVisitedSet.contains(currentPosition())) {
                    obstacleMatrix[obstaclePosition.first][obstaclePosition.second] = 'X'
                    break
                }
            }

            // reset everything and make the actual move
            charMatrix[obstaclePosition.first][obstaclePosition.second] = '.'
            resetPosition(previousPosition)
            move()
        }
        return obstacleMatrix.sumOf { row -> row.count { position -> position == 'X' } }
    }

    part1().println()
    part2().println()
}
