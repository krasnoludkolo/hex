import io.vavr.collection.HashMap
import io.vavr.collection.HashSet
import io.vavr.collection.Map
import io.vavr.collection.Set
import io.vavr.kotlin.hashSet

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty()
) {

    private fun empty(): HashSet<BoardCell>? = HashSet.empty()

    val left: List<LeftWallCell> = emptyList()
    val right: List<LeftWallCell> = emptyList()
    val down: List<LeftWallCell> = emptyList()
    val up: List<LeftWallCell> = emptyList()

    class BoardBuilder(private val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty()){

        fun addEdge(source: Cell, destination: Cell):BoardBuilder = BoardBuilder(
            adjacencyMap = adjacencyMap
                .put(source, hashSet(destination), { a, b -> a.addAll(b) })
                .put(destination, hashSet(source), { a, b -> a.addAll(b) })
        )

        fun build() = Board(adjacencyMap)

    }

}
