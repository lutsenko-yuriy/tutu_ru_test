package com.example.yurich.tuturutest.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/tutu-ru/hire_android_test/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}