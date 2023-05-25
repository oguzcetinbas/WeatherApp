package com.example.hw13_weatherapp.view.homeFragment

import android.content.Context
import com.example.hw13_weatherapp.db.WeatherPropertyDao
import com.example.hw13_weatherapp.db.WeatherPropertyDatabase
import com.example.hw13_weatherapp.model.api.WeatherApiService
import com.example.hw13_weatherapp.repo.WeatherLocalDataSource
import com.example.hw13_weatherapp.repo.WeatherPropertyRepository
import com.example.hw13_weatherapp.repo.WeatherRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    fun provideWeatherPropertyDao(database: WeatherPropertyDatabase): WeatherPropertyDao {
        return database.weatherPropertyDao()
    }

    @Provides
    fun provideLocalDataSource(dao: WeatherPropertyDao): WeatherLocalDataSource {
        return WeatherLocalDataSource(dao)
    }

    @Provides
    fun provideRemoteDataSource(apiService: WeatherApiService): WeatherRemoteDataSource {
        return WeatherRemoteDataSource(apiService)
    }

    @Provides
    fun provideWeatherRepo(
        @ApplicationContext context: Context,
        localDataSource: WeatherLocalDataSource,
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherPropertyRepository {
        return WeatherPropertyRepository(context,remoteDataSource,localDataSource)
    }

}