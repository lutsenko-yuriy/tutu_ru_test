package com.example.yurich.tuturutest.repository.local_storage.database

import com.pushtorefresh.storio.sqlite.queries.Query

/**
 * This class describes a table with stations.
 * The author of this class decided that table should
 * look like this after he found out that
 * some cities and cities at their stations have
 * different names
 */
object StationsTable {

    const val TABLE = "Stations"

    const val COLUMN_CITY_ID = "city_id"

    const val COLUMN_COUNTRY = "country"
    const val COLUMN_DISTRICT = "district"
    const val COLUMN_CITY = "city"
    const val COLUMN_REGION = "region"

    const val COLUMN_ID = "_id"

    const val COLUMN_STATION = "station"
    const val COLUMN_DIRECTION = "direction"

    const val PREFIXED_CITY_ID = "$TABLE.$COLUMN_CITY_ID"

    const val PREFIXED_COUNTRY = "$TABLE.$COLUMN_COUNTRY"
    const val PREFIXED_DISTRICT = "$TABLE.$COLUMN_DISTRICT"
    const val PREFIXED_CITY = "$TABLE.$COLUMN_CITY"
    const val PREFIXED_REGION = "$TABLE.$COLUMN_REGION"

    const val PREFIXED_ID = "$TABLE.$COLUMN_ID"

    const val PREFIXED_STATION = "$TABLE.$COLUMN_STATION"
    const val PREFIXED_DIRECTION = "$TABLE.$COLUMN_DIRECTION"

    val QUERY_ALL = Query.builder()
            .table(TABLE)
            .build()

    fun getCreateTableQuery(): String {
        return "CREATE TABLE $TABLE (" +
                "$COLUMN_CITY_ID INTEGER, " +
                "$COLUMN_COUNTRY TEXT NOT NULL, " +
                "$COLUMN_DISTRICT TEXT, " +
                "$COLUMN_CITY TEXT NOT NULL, " +
                "$COLUMN_REGION TEXT, " +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_STATION TEXT NOT NULL," +
                "$COLUMN_DIRECTION INTEGER NOT NULL," +
                "FOREIGN KEY($COLUMN_CITY_ID) REFERENCES ${CitiesTable.TABLE}(${CitiesTable.COLUMN_ID})" +
                ");"
    }
}