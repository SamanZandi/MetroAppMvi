package com.zandroid.metromvp.mvp.ui.station

import com.zandroid.metromvp.mvp.base.BasePresenter
import com.zandroid.metromvp.mvp.base.BaseView
import com.zandroid.metromvp.mvp.data.model.Line
import com.zandroid.metromvp.mvp.data.model.ResponseStation

interface StationContracts {

    interface View: BaseView {
        fun showStations(data: List<ResponseStation.ResponseStationItem>)
    }

    interface Presenter:BasePresenter{
        fun callAllStations(lineID:String)
      //  fun onDestroy()
    }
}