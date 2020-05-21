package game

import io.vavr.collection.HashMap
import io.vavr.collection.Map
import io.vavr.collection.Set
import io.vavr.kotlin.hashSet

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