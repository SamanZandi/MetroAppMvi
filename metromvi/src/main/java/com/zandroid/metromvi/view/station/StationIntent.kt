package com.zandroid.metromvi.view.station

sealed class StationIntent {
    data class LoadStations(val lineId:String):StationIntent()
}