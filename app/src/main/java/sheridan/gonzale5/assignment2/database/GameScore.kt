package sheridan.gonzale5.assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game_score")
data class GameScore(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "die_one")
    val die_one: Int,

    @ColumnInfo(name = "die_two")
    val die_two: Int,

    @ColumnInfo(name = "die_three")
    val die_three: Int,

    @ColumnInfo(name = "total")
    val total: Int
)
