package com.example.yurich.tuturutest.repository

import com.example.yurich.tuturutest.repository.RetrieveSpecification.SpecOptions.FROM_SERVER
import com.example.yurich.tuturutest.repository.local_storage.LocalDataStorage
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.remote_storage.RemoteDataStorage
import rx.Observable
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
    fun getStations(specification: RetrieveSpecification): Observable<List<StoragedEntity>> {
        if (specification.source == FROM_SERVER) {
            return remoteDataStorage.getStations(specification.direction)
        }
        throw Throwable("Wait for it! Local DB is not implemented yet!")
    }
}