package com.example.hw13_weatherapp.di

import android.content.Context
import android.os.Environment
import androidx.room.Room
import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.db.WeatherPropertyDatabase
import com.example.hw13_weatherapp.model.api.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherPropertyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherPropertyDatabase::class.java,
            "name_property_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val cache = Cache(Environment.getDownloadCacheDirectory(),10 * 1024 * 1024)


        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(providerInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun providerInterceptor() : Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("asdadasda", "asdasdasda")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}