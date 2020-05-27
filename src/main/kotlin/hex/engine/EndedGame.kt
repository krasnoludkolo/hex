package hex.engine

import hex.*
import io.vavr.collection.List

class EndedGame(
    private val board: Board,
    private val history: List<Move>,
    private val winner: Player
) : Game {

    override fun makeMove(move: Move): MoveResult = ErrorMove.endedGame()

    override fun getHistory(): List<Move> = history

    override fun getStatus(): GameStatus = GameStatus.ended(board, winner)

}