package com.zandroid.metromvp.mvp.base

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.annotation.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

open class BasePresenterImpl:BasePresenter {

    /*     @SuppressLint("KotlinNullnessAnnotation")
    @NonNull
        var disposable: Disposable?=null

    override fun onStop() {
        disposable?.let {
            it.dispose()
        }
    }*/
    override fun onStop() {
        CoroutineScope(Dispatchers.Main).launch {
          cancel()
        }
    }


}