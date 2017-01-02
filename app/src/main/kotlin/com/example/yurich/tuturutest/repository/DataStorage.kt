package com.example.yurich.tuturutest.repository

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import rx.Observable

/**
 * Created by yurich on 16.12.16.
 */
interface DataStorage {

    fun getStations(): Observable<StoragedStation>

    fun putStation(station: StoragedStation)

    fun clearDatabase()

}