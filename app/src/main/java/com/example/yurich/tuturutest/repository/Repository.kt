package com.example.yurich.tuturutest.repository

import android.content.Context
import android.util.Log
import com.example.yurich.tuturutest.repository.local_storage.LocalDataStorage
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.remote_storage.RemoteDataStorage
import rx.Observable
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

    fun getStations(): Observable<List<StoragedStation>> {
            val observable = getDataFromServer()

            return observable
    }

    private fun refreshDatabase(observable: Observable<List<StoragedStation>>) {
        observable
                .subscribe (
                        {
                            localDataStorage.putStations(it)
                        }
                )
    }

    private fun getDataFromServer(): Observable<List<StoragedStation>> {
        // This observable already works on
        // Schedulers.io()
        return remoteDataStorage
                .getStations()
    }

    private fun getDataFromDatabase(): Observable<List<StoragedStation>> {
        // This observable already works on
        // Schedulers.io()
        return localDataStorage
                .getStations()
    }
}