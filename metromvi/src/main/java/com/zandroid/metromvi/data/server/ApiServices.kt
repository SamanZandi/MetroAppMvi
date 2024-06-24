package com.zandroid.metromvi.data.server

import com.zandroid.metromvi.data.model.Line
import com.zandroid.metromvi.data.model.ResponseStation
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @GET("getLines.php")
    //  fun getLines(): Single<Response<List<Line.ResponseLinesItem>>>
    suspend fun getLines(): Response<List<Line.ResponseLinesItem>>

    @POST("getStations.php")
    @FormUrlEncoded
    suspend fun getStations(@Field("id") lineId:String): Response<List<ResponseStation.ResponseStationItem>>

}