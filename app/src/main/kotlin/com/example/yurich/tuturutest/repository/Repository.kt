package com.example.yurich.tuturutest.repository

import android.util.Log
import com.example.yurich.tuturutest.repository.local_storage.LocalDataStorage
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.remote_storage.RemoteDataStorage
import com.example.yurich.tuturutest.utils.DirectionConstants.ARRIVAL
import com.example.yurich.tuturutest.utils.DirectionConstants.DEPARTURE
import rx.Observable
import rx.schedulers.Schedulers
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

    interface LoadingProgressListener {
        fun onDone()
        fun onError()
    }

    var cachedDepartureStations = mutableListOf<StoragedStation>()
    var cachedArrivalStations = mutableListOf<StoragedStation>()

    fun getDepartureStations(filter: String): List<StoragedStation> {
        return cachedDepartureStations
                .filter {
                    it.stationTitle.contains(filter, true)
                }
    }

    fun getArrivalStations(filter: String): List<StoragedStation> {
        return cachedArrivalStations
                .filter {
                    it.stationTitle.contains(filter, true)
                }
    }

    fun refreshCachedStations(loadFromLocalDb: Boolean = true, listener: LoadingProgressListener) {
        if (loadFromLocalDb) {
            getDataFromDatabase()
        } else {
            getDataFromServerAndUpdateDatabase()
        }
                .subscribe(
                        {
                            cachedDepartureStations = mutableListOf<StoragedStation>()
                            cachedArrivalStations = mutableListOf<StoragedStation>()

                            cachedDepartureStations.addAll(it.filter{ it.direction == DEPARTURE })
                            cachedArrivalStations.addAll(it.filter{ it.direction == ARRIVAL })
                        },
                        {
                            Log.e("REPOSITORY", "Error occurred while loading")
                            listener.onError()
                        },
                        {
                            listener.onDone()

                        }
                )
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