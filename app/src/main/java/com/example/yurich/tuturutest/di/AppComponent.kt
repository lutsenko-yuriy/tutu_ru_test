package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.departure.DeparturePresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RemoteStorageModule::class
))
interface AppComponent {

    fun inject(presenter: DeparturePresenter)
//    fun inject(presenter: ArrivalsPresenter)
}