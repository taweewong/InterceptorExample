package com.taweewong.interceptorexample.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NetworkClient {
    fun getClient(interceptorList: List<Interceptor>): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            //Add http logging interceptor by default
            addInterceptor(getHttpLoggingInterceptor())
            interceptorList.forEach { interceptor ->
                addInterceptor(interceptor)
            }
        }.build()
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}