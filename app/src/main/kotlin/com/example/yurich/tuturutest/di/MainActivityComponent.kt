package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.schedule.ScheduleFragment.arrival.ArrivalsPresenter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.date.CalendarPresenter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.departure.DeparturePresenter
import dagger.Subcomponent

/**
 * Created by yurich on 02.01.17.
 */
@ActivityScope
@Subcomponent
interface MainActivityComponent {
    fun inject(presenter: DeparturePresenter)
    fun inject(presenter: ArrivalsPresenter)

    fun inject(presenter: CalendarPresenter)
}