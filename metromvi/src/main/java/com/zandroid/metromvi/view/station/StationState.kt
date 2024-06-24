package com.zandroid.metromvi.view.station

import com.zandroid.metromvi.data.model.ResponseStation

sealed class StationState {
    object Loading:StationState()

    data class ShowStations(val data:List<ResponseStation.ResponseStationItem>):StationState()
    data class ShowError(val message:String):StationState()
}