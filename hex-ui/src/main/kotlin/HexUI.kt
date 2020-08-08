import console.ConsoleUI
import hex.Board
import hex.status.GameHistory

interface HexUI {

    fun drawBoard(board: Board)
    fun drawHistory(gameHistory: GameHistory)

    companion object {

        fun consoleUI(): HexUI = ConsoleUI()

    }
}