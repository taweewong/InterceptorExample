package com.taweewong.interceptorexample.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class SortingHatInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())
        var stringBody = response.body()?.string()

        return if (response.isSuccessful) {
            while (stringBody != "\"Gryffindor\"") {
                response = chain.proceed(chain.request())
                stringBody = response.body()?.string()
            }
            response.newBuilder()
                    .code(response.code())
                    .body(ResponseBody.create(response.body()?.contentType(), stringBody))
                    .build()
        } else {
            response
        }
    }
}