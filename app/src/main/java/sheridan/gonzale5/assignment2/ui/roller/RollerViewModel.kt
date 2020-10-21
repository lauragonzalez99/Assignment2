package sheridan.gonzale5.assignment2.ui.roller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.gonzale5.assignment2.database.GameScore
import sheridan.gonzale5.assignment2.database.GameScoreDao
import sheridan.gonzale5.assignment2.database.GameScoreDatabase

class RollerViewModel(application: Application) : AndroidViewModel(application) {

    enum class Status { NEW_DATA, SAVED_DATA }

    data class State(val status: Status, val gameScoreId: Long?);

    companion object {
        val INITIAL_SATE: State = State(Status.NEW_DATA, null)
    }

    private val _state = MutableLiveData(INITIAL_SATE)
    val state: LiveData<State> = _state

    private val gameScoreDao: GameScoreDao =
        GameScoreDatabase.getInstance(application).gameScoreDao

    fun save(gameScore: GameScore){
        viewModelScope.launch {
            val gameScoreId : Long = gameScoreDao.insert(gameScore)
            _state.value = State(Status.SAVED_DATA, gameScoreId)
        }
    }

    fun reset(){
        _state.value = INITIAL_SATE
    }

}