package com.example.yurich.tuturutest.di.Modules

import com.example.yurich.tuturutest.repository.ResultQuery
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yurich on 19.12.16.
 */
@Module
class ResultQueryModule {
    @Provides
    @Singleton
    fun provideResultQuery(): ResultQuery {
        return ResultQuery()
    }
}