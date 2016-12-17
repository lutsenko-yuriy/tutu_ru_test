package com.example.yurich.tuturutest.repository.remote_storage

import retrofit2.Call

/**
 * Created by yurich on 14.12.16.
 */
interface StationsApi {
    fun getStationsList(): Call<ServerResponse>
}