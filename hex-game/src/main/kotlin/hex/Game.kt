package hex

import hex.engine.HexGame
import hex.status.EndedGameStatus
import hex.status.OngoingGameStatus

fun playGame(red: Player, blue: Player, size: Int): EndedGameStatus {
    val game = GameCreator.createGame(size)
    return playNextRound(GamePlayers(red, blue), game)
}

fun playNextRound(players: GamePlayers, game: HexGame): EndedGameStatus {
    return when (val gameStatus = game.getStatus()) {
        is EndedGameStatus -> gameStatus
        is OngoingGameStatus -> {
            val nextPlayerMove = players.activePlayer.makeMove(gameStatus)
            when (val moveResult = game.makeMove(nextPlayerMove)) {
                is Success -> playNextRound(players.nextActive(), moveResult.hexGame)
                is ErrorMove -> throw IllegalStateException("Im to lazy to handle this now")
            }
        }
    }
}


data class GamePlayers(
    val red: Player,
    val blue: Player,
    val activePlayer: Player = red
) {
    fun nextActive() = if (activePlayer == red) copy(activePlayer = blue) else copy(activePlayer = red)
}