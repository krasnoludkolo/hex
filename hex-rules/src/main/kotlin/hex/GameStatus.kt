package hex

import io.vavr.collection.List

sealed class GameStatus {

    abstract val board: Board

    companion object {
        fun ended(board: Board, winner: HexPlayer, history: List<Move>) = EndedGameStatus(board, winner, history)
        fun ongoing(board: Board, winner: HexPlayer) = OngoingGameStatus(board, winner)
    }
}

data class EndedGameStatus(
    override val board: Board,
    val winner: HexPlayer,
    val history: List<Move>
) : GameStatus()

data class OngoingGameStatus(
    override val board: Board,
    val activeHexPlayer: HexPlayer
) : GameStatus()