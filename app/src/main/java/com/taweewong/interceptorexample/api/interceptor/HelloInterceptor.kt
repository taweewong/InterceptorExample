package com.taweewong.interceptorexample.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class HelloInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        //Rewrite request
        val newRequest = originalRequest.newBuilder()
            .addHeader(
                "Content-Type",
                "application/json;charset=utf-8")
            .method(originalRequest.method(), originalRequest.body())
            .build()
        val response = chain.proceed(newRequest)
        //Rewrite response
        return response.newBuilder()
            .code(200)
            .body(ResponseBody.create(
                response.body()?.contentType(),
                "Hello Interceptor!"))
            .build()
    }
}