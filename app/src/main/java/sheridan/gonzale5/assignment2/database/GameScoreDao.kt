package sheridan.gonzale5.assignment2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface GameScoreDao {

    //getall
    @Query("SELECT * FROM game_score ORDER BY id")
    fun getAll() : LiveData<List<GameScore>>

    //deleteall
    @Query("DELETE FROM game_score")
    suspend fun deleteAll()

}