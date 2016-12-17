package com.example.yurich.tuturutest.repository.local_storage.database.resolvers.city

import android.database.Cursor
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.local_storage.database.CitiesTable
import com.example.yurich.tuturutest.repository.local_storage.database.StationsTable
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver

/**
 * Created by yurich on 17.12.16.
 */
class StationGetResolver : DefaultGetResolver<StoragedStation>() {

    override fun mapFromCursor(cursor: Cursor): StoragedStation {
        return StoragedStation(
                cursor.getString(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_COUNTRY)),
                cursor.getString(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_DISTRICT)),
                cursor.getLong(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_CITY_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_CITY)),
                cursor.getString(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_REGION)),

                cursor.getLong(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_REGION)),

                cursor.getInt(cursor.getColumnIndexOrThrow(StationsTable.COLUMN_DIRECTION))
        )
    }
}