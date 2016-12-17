package com.example.yurich.tuturutest.di

import com.example.yurich.tuturutest.repository.remote_storage.StationsApi
import com.example.yurich.tuturutest.repository.remote_storage.TutuServerApi
import com.example.yurich.tuturutest.repository.remote_storage.TutuStationsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Module(includes = arrayOf(NetworkModule::class))
class RemoteStorageModule {
    @Provides
    @Singleton
    fun providesStationsApi(serverApi: TutuServerApi): StationsApi {
        return TutuStationsApi(serverApi)
    }

    @Provides
    @Singleton
    fun providesServerApi(retrofit: Retrofit): TutuServerApi {
        return retrofit.create(TutuServerApi::class.java)
    }
}