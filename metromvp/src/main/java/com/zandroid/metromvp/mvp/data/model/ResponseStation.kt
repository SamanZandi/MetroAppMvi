package com.zandroid.metromvp.mvp.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class ResponseStation : ArrayList<ResponseStation.ResponseStationItem>(){
    @Parcelize
    data class ResponseStationItem(
        @SerializedName("addr")
        val addr: String?, // خیابان شریعتی نرسیده به میدان قدس
        @SerializedName("atm")
        val atm: String?, // 1
        @SerializedName("bus")
        val bus: String?, // 1
        @SerializedName("CrossLine_ID")
        val crossLineID: String?, // 0
        @SerializedName("elevator")
        val elevator: String?, // 0
        @SerializedName("escalator")
        val escalator: String?, // 1
        @SerializedName("id")
        val id: String?, // 1
        @SerializedName("latitude")
        val latitude: String?, // 35.8046493
        @SerializedName("LineId")
        val lineId: String?, // 1
        @SerializedName("longitude")
        val longitude: String?, // 51.4313206
        @SerializedName("lost")
        val lost: String?, // 1
        @SerializedName("OrderID")
        val orderID: String?, // 1
        @SerializedName("parking")
        val parking: String?, // 0
        @SerializedName("phone")
        val phone: String?, // 1
        @SerializedName("Station_Duration")
        val stationDuration: String?, // 2
        @SerializedName("taxi")
        val taxi: String?, // 1
        @SerializedName("ticket")
        val ticket: String?, // 1
        @SerializedName("Title")
        val title: String, // تجریش
        @SerializedName("Title_English")
        val titleEnglish: String?, // Tajrish
        @SerializedName("water")
        val water: String? // 1
    ):Parcelable
}