package alfabeta.evaluator

import hex.*
import io.vavr.Tuple2
import io.vavr.collection.HashSet
import io.vavr.collection.Set
import io.vavr.kotlin.getOrNull
import io.vavr.kotlin.toVavrList

class LongestClusterEvaluator : BoardEvaluator {

    override fun evaluate(board: Board): Double {
        val groupBy = board.piecesMap
            .groupBy { it._2 }
            .mapValues { it.keySet() }
            .toMap { t ->
                Tuple2(
                    t._1,
                    t._2
                        //TODO mozna zoptymalizować wybierając najpierw kandydatów na najdłuższych
                        // tak aby nie szukać dla każdego
                        .map { point -> findLongestPathLengthFrom(point to t._1, board) }
                        .sortedBy { it }
                        .first()
                )
            }
        return groupBy.get(RedPiece).getOrElse(0.toDouble()) - groupBy.get(BluePiece).getOrElse(0.toDouble())
    }

    private fun findLongestPathLengthFrom(piece: Pair<Point, Piece>, board: Board): Double {

        fun dfs(trace: Set<Cell>, current: Point): Double {
            val next = board.adjacencyMap
                .getOrElse(BoardCell(current), HashSet.empty())
                .removeAll(trace)
                .filterIsInstance<BoardCell>()
                .toVavrList()
                .filter { board.piecesMap.getOrNull(it.point)?.let { it == piece.second } ?: false }

            return if (next.isEmpty) {
                trace.size().toDouble()
            } else {
                next
                    .asSequence()
                    .filter { board.piecesMap.getOrNull(it.point) == piece.second }
                    .map { dfs(trace.add(it), it.point) }
                    .sorted()
                    .firstOrNull() ?: trace.size().toDouble()
            }
        }

        return dfs(HashSet.of(BoardCell(piece.first)), piece.first)
    }


}