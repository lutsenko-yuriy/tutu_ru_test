package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.di.Modules.*
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.arrival.ArrivalsPresenter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.date.CalendarPresenter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.departure.DeparturePresenter
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        RemoteStorageModule::class,
        ResultQueryModule::class
))
interface AppComponent {
    fun plusMainActivity(): MainActivityComponent
    fun plusSplashActivity(): SplashActivityComponent
}