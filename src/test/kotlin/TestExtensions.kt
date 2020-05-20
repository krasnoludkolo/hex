
fun Board.hasEdge(from: Pair<Int, Int>, to: Pair<Int, Int>): Boolean {
    return adjacencyMap[BoardCell(from)].get().contains(BoardCell(to))
}

fun Board.hasEdgeWithUp(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].get().contains(UpWallCell(n))
}

fun Board.hasEdgeWithDown(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].get().contains(DownWallCell(n))
}

fun Board.hasEdgeWithLeft(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].get().contains(LeftWallCell(n))
}

fun Board.hasEdgeWithRight(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].get().contains(RightWallCell(n))
}

