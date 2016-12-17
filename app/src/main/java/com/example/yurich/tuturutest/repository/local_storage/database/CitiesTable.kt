package com.example.yurich.tuturutest.repository.local_storage.database

import com.pushtorefresh.storio.sqlite.queries.Query

/**
 * This class describes a table with cities
 */
object CitiesTable {
    const val TABLE = "cities"

    const val COLUMN_ID = "_id"

    const val COLUMN_COUNTRY = "country"
    const val COLUMN_DISTRICT = "district"
    const val COLUMN_CITY = "city"
    const val COLUMN_REGION = "region"

    const val PREFIXED_ID = "$TABLE.$COLUMN_ID"

    const val PREFIXED_COUNTRY = "$TABLE.$COLUMN_COUNTRY"
    const val PREFIXED_DISTRICT = "$TABLE.$COLUMN_DISTRICT"
    const val PREFIXED_CITY = "$TABLE.$COLUMN_CITY"
    const val PREFIXED_REGION = "$TABLE.$COLUMN_REGION"

    val QUERY_ALL = Query.builder()
            .table(TABLE)
            .build()


    fun getCreateTableQuery(): String {
        return "CREATE TABLE $TABLE (" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_COUNTRY TEXT NOT NULL, " +
                "$COLUMN_DISTRICT TEXT, " +
                "$COLUMN_CITY TEXT NOT NULL, " +
                "$COLUMN_REGION TEXT" +
                ");"
    }
}