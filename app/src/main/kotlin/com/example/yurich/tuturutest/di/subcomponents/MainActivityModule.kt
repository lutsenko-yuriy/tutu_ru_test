package com.example.yurich.tuturutest.di.subcomponents

import com.example.yurich.tuturutest.di.ActivityScope
import com.example.yurich.tuturutest.schedule.MainActivity
import dagger.Module
import dagger.Provides

/**
 * Created by yurich on 05.01.17.
 */
@Module
class MainActivityModule(val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): MainActivity {
        return activity
    }

}