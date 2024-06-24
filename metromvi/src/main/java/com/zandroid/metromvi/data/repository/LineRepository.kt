package com.zandroid.metromvi.data.repository

import com.zandroid.metromvi.data.model.Line
import com.zandroid.metromvi.data.server.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LineRepository @Inject constructor(private val api:ApiServices) {
    suspend fun loadLines()=api.getLines()

}