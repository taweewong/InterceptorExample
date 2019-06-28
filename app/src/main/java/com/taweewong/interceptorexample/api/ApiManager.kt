package com.taweewong.interceptorexample.api

import com.taweewong.interceptorexample.api.interceptor.SortingHatInterceptor
import com.taweewong.interceptorexample.api.service.PotterService
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    fun potter() = initPotter(listOf(
        SortingHatInterceptor()
    )).create(PotterService::class.java)

    private fun initPotter(interceptorList: List<Interceptor>): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.potterapi.com/v1/")
            .client(NetworkClient().getClient(interceptorList))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
