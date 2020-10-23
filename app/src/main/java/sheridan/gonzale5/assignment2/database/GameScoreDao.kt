package sheridan.gonzale5.assignment2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameScoreDao {

    @Insert
    suspend fun insert(gamescore: GameScore): Long

    @Query("SELECT * FROM game_score ORDER BY id")
    fun getAll() : LiveData<List<GameScore>>

    @Query("DELETE FROM game_score")
    suspend fun deleteAll()

}