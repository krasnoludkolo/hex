import io.vavr.collection.HashMap
import io.vavr.collection.Map
import io.vavr.collection.Set
import io.vavr.kotlin.hashSet

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty()
) {

    val left: List<LeftWallCell> = adjacencyMap.values().filterIsInstance<LeftWallCell>()
    val right: List<RightWallCell> = adjacencyMap.values().filterIsInstance<RightWallCell>()
    val down: List<DownWallCell> = adjacencyMap.values().filterIsInstance<DownWallCell>()
    val up: List<UpWallCell> = adjacencyMap.values().filterIsInstance<UpWallCell>()

    class BoardBuilder(private val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty()) {

        fun addEdge(source: Cell, destination: Cell): BoardBuilder = if (source == destination) {
            this
        } else {
            BoardBuilder(
                adjacencyMap = adjacencyMap
                    .put(source, hashSet(destination), { a, b -> a.addAll(b) })
                    .put(destination, hashSet(source), { a, b -> a.addAll(b) })
            )
        }

        fun build() = Board(adjacencyMap)

    }

}
