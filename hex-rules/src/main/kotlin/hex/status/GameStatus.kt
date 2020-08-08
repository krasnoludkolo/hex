package hex.status

import hex.Board
import hex.HexPlayer

sealed class GameStatus {

    abstract val board: Board

    companion object {
        fun ended(board: Board, winner: HexPlayer, history: GameHistory) =
            EndedGameStatus(board, winner, history)

        fun ongoing(board: Board, winner: HexPlayer) =
            OngoingGameStatus(board, winner)
    }
}

data class EndedGameStatus(
    override val board: Board,
    val winner: HexPlayer,
    val history: GameHistory
) : GameStatus()

data class OngoingGameStatus(
    override val board: Board,
    val activeHexPlayer: HexPlayer
) : GameStatus()