package com.example.yurich.tuturutest.repository.local_storage.database.resolvers.city

import android.util.Log
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.database.CitiesTable
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver
import com.pushtorefresh.storio.sqlite.operations.put.PutResult

/**
 * Created by yurich on 17.12.16.
 */
class CityPutResolver : PutResolver<StoragedCity>() {
    lateinit var result: PutResult
    override fun performPut(storIOSQLite: StorIOSQLite, city: StoragedCity): PutResult {
        storIOSQLite
                .put()
                .`object`(city)
                .prepare()
                .asRxObservable()
                .subscribe(
                        { result = it }
                )

        return PutResult.newUpdateResult(result.numberOfRowsUpdated()!!, CitiesTable.TABLE)
    }
}