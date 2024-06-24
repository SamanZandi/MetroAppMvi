package com.zandroid.metromvi.view.line

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zandroid.metromvi.data.repository.LineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LineViewModel @Inject constructor(private val repository: LineRepository):ViewModel() {
  val lineIntent=Channel<LineIntent>()
  private val _state=MutableStateFlow<LineState>(LineState.Loading)
    val state:StateFlow<LineState> get() = _state



    init {
        handleIntent()
    }

   fun handleIntent()=viewModelScope.launch {
        lineIntent.consumeAsFlow().collect{
            when(it){
                is LineIntent.LoadLineList->loadLines()
            }
        }
    }

   fun loadLines()=viewModelScope.launch {
       val response=repository.loadLines()
       _state.value=LineState.Loading
       when(response.code()){
           in 200..202->{
               _state.emit(LineState.ShowLineList(response.body()!!))
           }
           in 400..499->{_state.emit(LineState.ShowError(response.message()))}
           in 500..599->{_state.emit(LineState.ShowError(response.message()))}
       }
   }


}