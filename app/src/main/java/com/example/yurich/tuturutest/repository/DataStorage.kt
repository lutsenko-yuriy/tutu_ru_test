package com.example.yurich.tuturutest.repository

import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import rx.Observable

/**
 * Created by yurich on 16.12.16.
 */
interface DataStorage {

    fun getStations(direction: Int, query: String = ""): Observable<List<StoragedEntity>>

    fun putStations(listOfEntities: List<StoragedEntity>)

}