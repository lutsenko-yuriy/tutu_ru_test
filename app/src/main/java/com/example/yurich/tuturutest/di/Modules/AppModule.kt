package com.example.yurich.tuturutest.di.Modules

import android.content.Context
import com.example.yurich.tuturutest.StationsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */

@Module
class AppModule(val app: StationsApp) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApp() = app
}