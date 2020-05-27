import hex.*
import hex.engine.Game
import io.vavr.collection.List
import io.vavr.kotlin.getOrNull

fun Board.hasEdge(from: Pair<Int, Int>, to: Pair<Int, Int>): Boolean {
    return adjacencyMap.getOrNull(BoardCell(from))?.contains(BoardCell(to)) ?: false
}

fun Board.hasEdgeWithUp(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap.getOrNull(BoardCell(from))?.contains(UpWallCell(n)) ?: false
}

fun Board.hasEdgeWithDown(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap.getOrNull(BoardCell(from))?.contains(DownWallCell(n)) ?: false
}

fun Board.hasEdgeWithLeft(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap.getOrNull(BoardCell(from))?.contains(LeftWallCell(n)) ?: false
}

fun Board.hasEdgeWithRight(from: Pair<Int, Int>, n: Int): Boolean {
    return adjacencyMap.getOrNull(BoardCell(from))?.contains(RightWallCell(n)) ?: false
}

fun switch() = SwitchMove
fun blue(x: Int, y: Int) = NormalMove.blue(Point(x, y))
fun red(x: Int, y: Int) = NormalMove.red(Point(x, y))

fun Board.getPieceAt(point: Point) = piecesMap.getOrNull(point)

fun Game.makeMoves(vararg moves: Move) =
    List.ofAll(moves.toList()).fold(this) { board, move -> (board.makeMove(move) as Success).game }