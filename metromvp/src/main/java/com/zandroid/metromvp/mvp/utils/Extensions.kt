package com.zandroid.metromvp.mvp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zandroid.metromvp.R

//Check Network
fun Context.isNetworkAvailable():Boolean{
    val cm=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info=cm.activeNetworkInfo
    return info !=null && info.isConnected
}

//SnackBar
fun View.showSnackBar(message:String){
    Snackbar.make(this,message, Snackbar.LENGTH_SHORT).show()
}

//Visibility
fun View.isVisible(isVisible:Boolean){
    if (isVisible){
        this.visibility= View.VISIBLE
    }else{
        this.visibility= View.GONE
    }
}

//recyclerView
fun RecyclerView.recyclerViewApply(adapter: RecyclerView.Adapter<*>, layoutManager: RecyclerView.LayoutManager){
    this.adapter=adapter
    this.layoutManager=layoutManager
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