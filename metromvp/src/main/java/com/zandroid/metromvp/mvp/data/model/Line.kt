package com.zandroid.metromvp.mvp.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class Line : ArrayList<Line.ResponseLinesItem>() {
    @Parcelize
    data class ResponseLinesItem(
        @SerializedName("EnglishTitle")
        val englishTitle: String, // Tajrish / Kahrizak
        @SerializedName("id")
        val id: String, // 1
        @SerializedName("title")
        val title: String // تجریش / کهریزک
    ):Parcelable

    }
