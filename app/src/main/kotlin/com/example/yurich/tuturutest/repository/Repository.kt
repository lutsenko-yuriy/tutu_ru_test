package com.example.yurich.tuturutest.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.yurich.tuturutest.repository.local_storage.LocalDataStorage
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import com.example.yurich.tuturutest.repository.remote_storage.RemoteDataStorage
import com.example.yurich.tuturutest.utils.fromLocalDb
import com.example.yurich.tuturutest.utils.isDbReseted
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
class Repository @Inject constructor(
        val remoteDataStorage: RemoteDataStorage,
        val localDataStorage: LocalDataStorage
) {

    fun getStations(loadFromLocalDb: Boolean = true): Observable<DisplayedStation> {
        val observable = if (loadFromLocalDb) {
            getDataFromDatabase()
        } else {
            getDataFromServerAndUpdateDatabase()
        }
        return observable.doOnError {
                    Log.v("REPOSITORY", it.message)
                }
                .map(::DisplayedStation)
    }

    private fun getDataFromServerAndUpdateDatabase(): Observable<StoragedStation> {
        Log.v("REPOSITORY", "Loading from server")
        localDataStorage.clearDatabase()
        return remoteDataStorage
                .getStations()
                .doOnNext {
                    localDataStorage.putStation(it)
                }
    }

    private fun getDataFromDatabase(): Observable<StoragedStation> {
        Log.v("REPOSITORY", "Loading from local storage")
        return localDataStorage
                .getStations()
    }
}