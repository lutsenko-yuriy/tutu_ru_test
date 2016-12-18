package com.example.yurich.tuturutest.repository.local_storage

import com.example.yurich.tuturutest.repository.DataStorage
import com.example.yurich.tuturutest.repository.local_storage.database.StationsTable
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
class LocalDataStorage @Inject constructor(val stationsDb: StorIOSQLite) : DataStorage {

    override fun getStations(): Observable<List<StoragedStation>> {
        val observable = stationsDb
                .get()
                .listOfObjects(StoragedStation::class.java)
                .withQuery(
                        StationsTable.QUERY_ALL
                )
                .prepare()
                .asRxObservable()

        return observable

    }

    override fun putStations(listOfStations: List<StoragedStation>) {

        stationsDb
                .put()
                .objects(listOfStations)
                .prepare()
                .asRxObservable()
                .subscribe()

    }

}