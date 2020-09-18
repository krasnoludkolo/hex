package main

import HexUI
import alfabeta.AlfaBeta
import alfabeta.evaluator.LongestClusterEvaluator
import hex.BlueHexPlayer
import hex.RedHexPlayer
import hex.playGame
import random.RandomBot

fun main() {
    val red = AlfaBeta("red", 3, RedHexPlayer, LongestClusterEvaluator())
    val blue = RandomBot("blue", BlueHexPlayer)

    val (board, winner, history) = playGame(red, blue, 5)

    println("Winner: $winner")

    val consoleUI = HexUI.consoleUI()
    consoleUI.drawHistory(history)
    consoleUI.drawBoard(board)
}