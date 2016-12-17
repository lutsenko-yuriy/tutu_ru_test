package com.example.yurich.tuturutest.di

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.example.yurich.tuturutest.repository.local_storage.StoragedCity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.repository.local_storage.database.StationsDbHelper
import com.example.yurich.tuturutest.repository.local_storage.database.resolvers.city.*
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite
import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yurich on 17.12.16.
 */
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(sqLiteOpenHelper: SQLiteOpenHelper): StorIOSQLite {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(
                        StoragedCity::class.java,
                        SQLiteTypeMapping.builder<StoragedCity>()
                                .putResolver(CityPutResolver())
                                .getResolver(CityGetResolver())
                                .deleteResolver(CityDeleteResolver())
                                .build()
                )
                .addTypeMapping(
                        StoragedStation::class.java,
                        SQLiteTypeMapping.builder<StoragedStation>()
                                .putResolver(StationPutResolver())
                                .getResolver(StationGetResolver())
                                .deleteResolver(StationDeleteResolver())
                                .build()
                )
                .build()
    }

    @Provides
    @Singleton
    fun provideDbHelper(context: Context): SQLiteOpenHelper {
        return StationsDbHelper(context)
    }
}