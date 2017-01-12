package com.example.yurich.tuturutest.di.subcomponents

import com.example.yurich.tuturutest.di.ActivityScope
import com.example.yurich.tuturutest.splash.SplashActivity
import dagger.Module
import dagger.Provides

/**
 * Created by yurich on 05.01.17.
 */
@Module
class SplashActivityModule(val activity: SplashActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): SplashActivity {
        return activity
    }

}