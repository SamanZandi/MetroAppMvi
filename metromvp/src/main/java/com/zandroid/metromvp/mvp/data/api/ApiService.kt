package com.zandroid.metromvp.mvp.data.api

import com.zandroid.metromvp.mvp.data.model.Line
import com.zandroid.metromvp.mvp.data.model.Line.ResponseLinesItem
import com.zandroid.metromvp.mvp.data.model.ResponseStation
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("getLines.php")
 //  fun getLines(): Single<Response<List<Line.ResponseLinesItem>>>
   suspend fun getLines(): Response<List<Line.ResponseLinesItem>>

    @POST("getStations.php")
    @FormUrlEncoded
    suspend fun getStations(@Field("id") lineId:String):Response<List<ResponseStation.ResponseStationItem>>

  /*  @POST("getStations.php")
    @FormUrlEncoded
    suspend fun getStationsInfo(@Field("id") stationId:String):Response<ResponseStation.ResponseStationItem>*/
}