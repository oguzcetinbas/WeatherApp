package com.example.hw13_weatherapp.constants

import okhttp3.Interceptor
import okhttp3.Response

class MyCustomInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val myRequest = chain.request().newBuilder()
            .addHeader("Authorization",",,,,,")
            .build()
        return chain.proceed(myRequest)
    }
}