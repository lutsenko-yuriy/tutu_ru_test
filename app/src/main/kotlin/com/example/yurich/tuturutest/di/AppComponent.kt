package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.di.Modules.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RemoteStorageModule::class,
        ResultQueryModule::class
))
interface AppComponent {
    fun plusMainActivity(): MainActivityComponent
    fun plusSplashActivity(): SplashActivityComponent
}