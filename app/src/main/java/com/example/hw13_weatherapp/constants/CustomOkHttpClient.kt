package com.example.hw13_weatherapp.constants

import android.os.Environment
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class CustomOkHttpClient() : OkHttpClient() {
    fun onCreate() : OkHttpClient{
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val cache = Cache(Environment.getDownloadCacheDirectory(),10 * 1024 * 1024)


        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(MyCustomInterceptor())
            .build()
    }
}