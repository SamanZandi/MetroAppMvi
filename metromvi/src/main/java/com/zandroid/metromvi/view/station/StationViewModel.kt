package com.zandroid.metromvi.view.station

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zandroid.metromvi.data.repository.LineRepository
import com.zandroid.metromvi.data.repository.RepositoryStation
import com.zandroid.metromvi.view.line.LineIntent
import com.zandroid.metromvi.view.line.LineState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(private val repository: RepositoryStation): ViewModel() {
    val stationIntent= Channel<StationIntent>()
    private val _state= MutableStateFlow<StationState>(StationState.Loading)
    val state: StateFlow<StationState> get() = _state


    init {
        handleIntent()
    }

    fun handleIntent()=viewModelScope.launch {
        stationIntent.consumeAsFlow().collect{
            when(it){
                is StationIntent.LoadStations->loadStations(it.lineId)
            }
        }
    }

    fun loadStations(lineId:String)=viewModelScope.launch {
        val response=repository.loadStations(lineId)
        _state.value= StationState.Loading
        when(response.code()){
            in 200..202->{
                _state.emit(StationState.ShowStations(response.body()!!))
            }
            in 400..499->{_state.emit(StationState.ShowError(response.message()))}
            in 500..599->{_state.emit(StationState.ShowError(response.message()))}
        }
    }
}