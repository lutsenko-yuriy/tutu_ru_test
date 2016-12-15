package com.example.yurich.tuturutest.network

import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit interface of server
 */
interface TutuServerApi {
    @GET("allStations.json")
    fun getStationsList(): Call<ServerResponse>
}