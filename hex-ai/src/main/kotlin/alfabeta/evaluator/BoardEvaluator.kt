package alfabeta.evaluator

import hex.Board

interface BoardEvaluator {

    fun evaluate(board: Board): Double

}