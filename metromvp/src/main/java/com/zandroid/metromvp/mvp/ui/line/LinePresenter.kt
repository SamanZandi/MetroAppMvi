package com.zandroid.metromvp.mvp.ui.line

import com.zandroid.metromvp.mvp.base.BasePresenterImpl
import com.zandroid.metromvp.mvp.data.model.Line
import com.zandroid.metromvp.mvp.data.repository.LineRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LinePresenter @Inject constructor(private var view: LineContracts.View

,private val repository: LineRepository):LineContracts.Presenter,BasePresenterImpl() {


    override fun callAllLines() {
        //check Internet
   /*   if (view.isInternetAvailable()) {
            view.showLoading()
            disposable = repository.loadLines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response->
                    view.hideLoading()
                    when(response.code()){
                        in 200..202->{
                            response.body()?.let {
                                if (it.isNotEmpty()){
                                    view.showLines(it)
                                }

                            }
                        }
                    }
                },{err->
                    view.hideLoading()
                    view.showServerError(err.message.toString())
                })
        }else{
            view.hideLoading()
            view.showInternetError(false)
        }*/

       //Without RX
        CoroutineScope(Dispatchers.Main).launch{
            if (view.isInternetAvailable()){
                val response= withContext(Dispatchers.IO) {
                    repository.loadLines()
                }
                if (response.isSuccessful){
                    val lines=response.body()
                    if (lines.isNullOrEmpty().not()){
                        view.showLines(lines!!)
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