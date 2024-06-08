package com.zandroid.metromvp.mvp.data.repository

import com.zandroid.metromvp.mvp.data.api.ApiService
import javax.inject.Inject

class StationRepository @Inject constructor(private val api: ApiService) {
    suspend fun loadStations(lineId:String)=api.getStations(lineId)

    //suspend fun loadStationInfo(stationId:String)=api.getStationsInfo(stationId)
}