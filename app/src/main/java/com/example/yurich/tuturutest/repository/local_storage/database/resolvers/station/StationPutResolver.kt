package com.example.yurich.tuturutest.repository.local_storage.database.resolvers.city

import android.util.Log
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.local_storage.database.StationsTable
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver
import com.pushtorefresh.storio.sqlite.operations.put.PutResult

/**
 * Created by yurich on 17.12.16.
 */
class StationPutResolver : PutResolver<StoragedStation>() {
    override fun performPut(storIOSQLite: StorIOSQLite, station: StoragedStation): PutResult {
        val result = storIOSQLite
                .put()
                .`object`(station)
                .prepare()
                .executeAsBlocking()

        return PutResult.newUpdateResult(result.numberOfRowsUpdated()!!, StationsTable.TABLE)
    }
}