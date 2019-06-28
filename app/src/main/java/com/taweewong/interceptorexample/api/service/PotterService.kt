package com.taweewong.interceptorexample.api.service

import retrofit2.Call
import retrofit2.http.GET

interface PotterService {
    @GET("sortingHat")
    fun getHouseFromSortingHat(): Call<String>
}