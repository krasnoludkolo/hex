import io.vavr.collection.HashSet

fun Board.hasEdge(from: Pair<Int, Int>, to: Pair<Int, Int>): Boolean {
    return adjacencyMap[BoardCell(from)].getOrElse(HashSet.empty()).contains(BoardCell(to))
}

fun Board.hasEdgeWithUp(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].getOrElse(HashSet.empty()).contains(UpWallCell(n))
}

fun Board.hasEdgeWithDown(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].getOrElse(HashSet.empty()).contains(DownWallCell(n))
}

fun Board.hasEdgeWithLeft(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].getOrElse(HashSet.empty()).contains(LeftWallCell(n))
}

fun Board.hasEdgeWithRight(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap[BoardCell(from)].getOrElse(HashSet.empty()).contains(RightWallCell(n))
}

