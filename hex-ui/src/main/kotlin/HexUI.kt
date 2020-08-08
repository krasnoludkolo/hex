import console.ConsoleUI
import hex.Board

interface HexUI {

    fun drawBoard(board: Board)

    companion object {

        fun consoleUI(): HexUI = ConsoleUI()

    }
}