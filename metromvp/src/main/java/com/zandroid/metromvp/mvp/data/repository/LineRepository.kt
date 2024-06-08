package com.zandroid.metromvp.mvp.data.repository

import com.zandroid.metromvp.mvp.data.api.ApiService
import javax.inject.Inject

class LineRepository @Inject constructor(private val api:ApiService) {
     suspend fun loadLines()=api.getLines()
}