package com.example.yurich.tuturutest.di.subcomponents

import com.example.yurich.tuturutest.di.ActivityScope
import com.example.yurich.tuturutest.splash.RetainFragment
import dagger.Subcomponent

/**
 * Created by yurich on 02.01.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(
        SplashActivityModule::class
))
interface SplashActivityComponent {
    fun inject(fragment: RetainFragment)
}