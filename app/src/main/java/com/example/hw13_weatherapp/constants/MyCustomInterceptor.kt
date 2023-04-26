package com.example.hw13_weatherapp.constants

import okhttp3.Interceptor
import okhttp3.Response

class MyCustomInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val MyRequest = chain.request().newBuilder()
            .addHeader("Authorization","adadagkljlkasdjklahflasd")
            .build()
        return chain.proceed(MyRequest)
    }
}