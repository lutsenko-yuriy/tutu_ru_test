package com.example.yurich.tuturutest.repository.local_storage.database.resolvers.city

import android.database.Cursor
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.database.CitiesTable
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver

/**
 * Created by yurich on 17.12.16.
 */
class CityGetResolver : DefaultGetResolver<StoragedCity>() {

    override fun mapFromCursor(cursor: Cursor): StoragedCity {
        return StoragedCity(
                cursor.getString(cursor.getColumnIndexOrThrow(CitiesTable.COLUMN_COUNTRY)),
                cursor.getString(cursor.getColumnIndexOrThrow(CitiesTable.COLUMN_DISTRICT)),
                cursor.getLong(cursor.getColumnIndexOrThrow(CitiesTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(CitiesTable.COLUMN_CITY)),
                cursor.getString(cursor.getColumnIndexOrThrow(CitiesTable.COLUMN_REGION))
        )
    }
}