package com.example.yurich.tuturutest.repository.local_storage

import com.example.yurich.tuturutest.repository.DataStorage
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Singleton
class LocalDataStorage @Inject constructor(val stationsDb: StorIOSQLite) : DataStorage {

    override fun getStations(direction: Int, query: String): Observable<List<StoragedEntity>> {
        return Observable.create<List<StoragedEntity>> {}
    }

    override fun putStations(listOfEntities: List<StoragedEntity>) {
        stationsDb
                .put()
                .objects(
                        listOfEntities
                                .filter { it is StoragedCity }
                                .map { it as StoragedCity }
                )
                .prepare()
                .asRxObservable()
                .subscribe()

        stationsDb
                .put()
                .objects(
                        listOfEntities
                                .filter { it is StoragedStation }
                                .map { it as StoragedStation }
                )
                .prepare()
                .asRxObservable()
                .subscribe()

    }

}