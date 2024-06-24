package com.zandroid.metromvi.utils.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import com.zandroid.metromvi.utils.network.ConnectivityStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkConnectivity @Inject constructor(private val manager:ConnectivityManager,
                                              private val request:NetworkRequest):
    ConnectivityStatus {

    override fun observe(): Flow<ConnectivityStatus.Status> {
      return callbackFlow {
          val callBack=object :ConnectivityManager.NetworkCallback(){
              override fun onAvailable(network: Network) {
                  super.onAvailable(network)
                  //چون داخل فلو هستیم
                  launch {
                      send(ConnectivityStatus.Status.AVAILABLE)
                  }

              }

              override fun onUnavailable() {
                  super.onUnavailable()
                  launch {
                      send(ConnectivityStatus.Status.UNAVAILABLE)
                  }
              }

              override fun onLosing(network: Network, maxMsToLive: Int) {
                  super.onLosing(network, maxMsToLive)
                  launch {
                      send(ConnectivityStatus.Status.LOSING)
                  }
              }

              override fun onLost(network: Network) {
                  super.onLost(network)
                  launch {
                      send(ConnectivityStatus.Status.LOST)
                  }
              }
          }
          //اگر بالای 24 بود
          //register
          if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
              manager.registerDefaultNetworkCallback(callBack)
          }else{
              manager.registerNetworkCallback(request,callBack)
          }

            //unRegister
          awaitClose {
              manager.unregisterNetworkCallback(callBack)
          }
      }.distinctUntilChanged()

    }


}