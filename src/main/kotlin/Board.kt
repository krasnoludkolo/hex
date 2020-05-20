import io.vavr.collection.HashMap
import io.vavr.collection.Map
import io.vavr.collection.Set

data class Board(
    val adjacencyMap: Map<Cell, Set<Cell>> = HashMap.empty()
) {

    val left: List<LeftWallCell> = adjacencyMap.values().filterIsInstance<LeftWallCell>()
    val right: List<RightWallCell> = adjacencyMap.values().filterIsInstance<RightWallCell>()
    val down: List<DownWallCell> = adjacencyMap.values().filterIsInstance<DownWallCell>()
    val up: List<UpWallCell> = adjacencyMap.values().filterIsInstance<UpWallCell>()



}
