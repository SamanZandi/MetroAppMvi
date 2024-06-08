package com.zandroid.metromvp.mvp.utils.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zandroid.metromvp.mvp.data.api.ApiService
import com.zandroid.metromvp.mvp.utils.BASE_URL
import com.zandroid.metromvp.mvp.utils.NETWORK_TIME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideNetworkTime() = NETWORK_TIME


    @Singleton
    @Provides
    fun provideGson():Gson=GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideBodyInterceptor()=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(time:Long,body:HttpLoggingInterceptor)=OkHttpClient.Builder()
        .addInterceptor(body)
        .connectTimeout(time,TimeUnit.SECONDS)
        .readTimeout(time,TimeUnit.SECONDS)
        .writeTimeout(time,TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl:String, gson: Gson,client: OkHttpClient):ApiService=
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
          // .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)

}