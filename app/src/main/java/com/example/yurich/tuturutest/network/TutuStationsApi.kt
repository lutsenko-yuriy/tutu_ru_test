package com.example.yurich.tuturutest.network

import retrofit2.Call

/**
 * Created by yurich on 14.12.16.
 */
class TutuStationsApi(val tutuServerApi: TutuServerApi) : StationsApi {
    override fun getStationsList(): Call<ServerResponse> {
        return tutuServerApi.getStationsList()
    }
}