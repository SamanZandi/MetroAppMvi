package com.zandroid.metromvi.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zandroid.metromvi.R

fun View.visible(isShowLoading:Boolean,content:View){
    if (isShowLoading){
        this.visibility=View.VISIBLE
        content.visibility=View.GONE
    }else{
        this.visibility=View.GONE
        content.visibility=View.VISIBLE
    }
}

fun RecyclerView.setupRecyclerView(layoutManager: RecyclerView.LayoutManager,adapter:RecyclerView.Adapter<*>){
    this.layoutManager=layoutManager
    this.adapter=adapter
    this.setHasFixedSize(true)
    this.isNestedScrollingEnabled=false
}


//Facilities
@SuppressLint("ResourceAsColor")
fun ImageView.facilitiesIncluded(keyValue:String, color:Int){
    if(keyValue == "1"){
        this.setColorFilter(color)
    }else if (keyValue == "0"){
        this.setColorFilter(R.color.whiteSmoke)
    }
}