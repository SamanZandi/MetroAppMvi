package com.zandroid.metromvi.data.repository

import com.zandroid.metromvi.data.server.ApiServices
import javax.inject.Inject

class RepositoryStation @Inject constructor(private val api:ApiServices) {
    suspend fun loadStations(lineId:String)=api.getStations(lineId)
}