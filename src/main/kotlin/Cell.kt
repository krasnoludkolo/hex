sealed class Cell

data class BoardCell(val point: Point) : Cell() {
    constructor(p: Pair<Int, Int>) : this(Point(p.first, p.second))
}

data class UpWallCell(val n:Int) : Cell()
data class DownWallCell(val n:Int) : Cell()
data class LeftWallCell(val n:Int) : Cell()
data class RightWallCell(val n:Int) : Cell()

