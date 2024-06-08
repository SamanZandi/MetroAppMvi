package com.zandroid.metromvp.mvp.base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun isInternetAvailable():Boolean
    fun showInternetError(hasNet:Boolean)
    fun showServerError(err:String)

}