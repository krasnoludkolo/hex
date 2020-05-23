package game

sealed class GameStatus {
    companion object {
        fun ended(board: Board, winner: Player) = EndedGameStatus(board, winner)
        fun ongoing(board: Board, winner: Player) = OngoingGameStatus(board, winner)
    }
}

data class EndedGameStatus(
    val board: Board,
    val winner: Player
) : GameStatus()

data class OngoingGameStatus(
    val board: Board,
    val activePlayer: Player
) : GameStatus()