package com.zandroid.metromvp.mvp.ui.station

import com.zandroid.metromvp.mvp.base.BasePresenterImpl
import com.zandroid.metromvp.mvp.data.repository.LineRepository
import com.zandroid.metromvp.mvp.data.repository.StationRepository
import com.zandroid.metromvp.mvp.ui.line.LineContracts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StationPresenter @Inject constructor(private var view: StationContracts.View
, private val repository: StationRepository): StationContracts.Presenter, BasePresenterImpl()  {

    override fun callAllStations(lineID: String) {
        CoroutineScope(Dispatchers.Main).launch{
            if (view.isInternetAvailable()){
                val response= withContext(Dispatchers.IO) {
                    repository.loadStations(lineID)
                }
                if (response.isSuccessful){
                    val stations=response.body()
                    if (stations.isNullOrEmpty().not()){
                        if (stations != null) {
                            view.showStations(stations)
                        }
                    }else{
                        view.showServerError("response body is null")
                    }
                }else{
                    view.showServerError(response.message())
                }
            }
        }
    }



}