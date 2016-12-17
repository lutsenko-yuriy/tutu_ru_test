package com.example.yurich.tuturutest.repository.local_storage.database.resolvers.city

import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.database.CitiesTable
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResolver
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult

/**
 * Created by yurich on 17.12.16.
 */
class CityDeleteResolver : DeleteResolver<StoragedCity>() {

    lateinit var result: DeleteResult

    override fun performDelete(storIOSQLite: StorIOSQLite, station: StoragedCity): DeleteResult {
        storIOSQLite
                .delete()
                .`object`(station)
                .prepare()
                .asRxObservable()
                .subscribe {
                    result = it
                }

        return DeleteResult.newInstance(result.numberOfRowsDeleted(), CitiesTable.TABLE)
    }
}