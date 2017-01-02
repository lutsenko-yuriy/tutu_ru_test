package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.splash_fragment.RetainFragment
import dagger.Subcomponent

/**
 * Created by yurich on 02.01.17.
 */
@ActivityScope
@Subcomponent
interface SplashActivityComponent {
    fun inject(fragment: RetainFragment)
}