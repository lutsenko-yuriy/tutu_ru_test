package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.di.Modules.*
import com.example.yurich.tuturutest.di.subcomponents.MainActivityComponent
import com.example.yurich.tuturutest.di.subcomponents.MainActivityModule
import com.example.yurich.tuturutest.di.subcomponents.SplashActivityComponent
import com.example.yurich.tuturutest.di.subcomponents.SplashActivityModule
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.splash.SplashActivity
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
    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: SplashActivityModule): SplashActivityComponent
}