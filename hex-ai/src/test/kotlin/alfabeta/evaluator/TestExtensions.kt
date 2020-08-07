package alfabeta.evaluator

import hex.*
import hex.engine.HexGame
import io.vavr.collection.List
import io.vavr.kotlin.getOrNull

fun switch() = SwitchMove
fun blue(x: Int, y: Int) = NormalMove.blue(Point(x, y))
fun red(x: Int, y: Int) = NormalMove.red(Point(x, y))

fun Board.getPieceAt(point: Point) = piecesMap.getOrNull(point)

fun HexGame.makeMoves(vararg moves: Move) =
    List.ofAll(moves.toList()).fold(this) { board, move -> (board.makeMove(move) as Success).hexGame }