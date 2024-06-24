package com.zandroid.metromvi.utils.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityStatus {
    enum class Status {AVAILABLE, UNAVAILABLE, LOSING,LOST}

    fun observe():Flow<Status>

}