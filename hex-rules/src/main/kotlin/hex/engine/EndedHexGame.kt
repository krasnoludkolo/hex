package hex.engine

import hex.*
import hex.status.GameHistory
import hex.status.GameStatus

class EndedHexGame(
    private val board: Board,
    private val history: GameHistory,
    private val winner: HexPlayer
) : HexGame {

    override fun makeMove(move: Move): MoveResult = ErrorMove.endedGame()

    override fun getHistory(): GameHistory = history

    override fun getStatus(): GameStatus = GameStatus.ended(board, winner, history)

}