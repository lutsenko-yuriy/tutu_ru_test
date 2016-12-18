package com.example.yurich.tuturutest.repository.local_storage.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by yurich on 17.12.16.
 */
class StationsDbHelper(val context: Context) : SQLiteOpenHelper(context, "Stations", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(StationsTable.getCreateTableQuery())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // Just update. No more, no less!
    }
}