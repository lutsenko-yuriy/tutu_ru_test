package com.example.yurich.tuturutest.repository

import android.util.Log
import com.example.yurich.tuturutest.repository.cached_storage.CachedDataStorage
import com.example.yurich.tuturutest.repository.local_storage.LocalDataStorage
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.remote_storage.RemoteDataStorage
import com.example.yurich.tuturutest.utils.DirectionConstants.ARRIVAL
import com.example.yurich.tuturutest.utils.DirectionConstants.DEPARTURE
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
class Repository @Inject constructor(
        val remoteDataStorage: RemoteDataStorage,
        val localDataStorage: LocalDataStorage,
        val cachedDataStorage: CachedDataStorage
) {

    interface LoadingProgressListener {
        fun onDone()
        fun onError()
    }

    fun getDepartureStations(filter: String = ""): List<StoragedStation> {
        return cachedDataStorage.getDepartureStations(filter)
    }

    fun getArrivalStations(filter: String = ""): List<StoragedStation> {
        return cachedDataStorage.getArrivalStations(filter)
    }

    fun refreshCachedStations(loadFromLocalDb: Boolean = true, listener: LoadingProgressListener?) {
        getStationsSource(loadFromLocalDb)
                .subscribe(
                        {
                            cachedDataStorage.refreshCache(it)
                        },
                        {
                            Log.e("REPOSITORY", "Error occurred while loading")
                            listener?.onError()
                        },
                        {
                            listener?.onDone()
                        }
                )
    }

    private fun getStationsSource(loadFromLocalDb: Boolean): Observable<List<StoragedStation>> {
        return if (loadFromLocalDb) {
            getDataFromDatabase()
        } else {
            getDataFromServerAndUpdateDatabase()
        }
    }

    private fun getDataFromServerAndUpdateDatabase(): Observable<List<StoragedStation>> {
        Log.v("REPOSITORY", "Loading from server")
        localDataStorage.clearDatabase()
        return remoteDataStorage
                .getStations()
                .doOnNext {
                    localDataStorage.putStations(it)
                }
                .subscribeOn(Schedulers.io())
    }

    private fun getDataFromDatabase(): Observable<List<StoragedStation>> {
        Log.v("REPOSITORY", "Loading from local storage")
        return localDataStorage
                .getStations()
    }
}