package com.example.yurich.tuturutest.repository

/**
 * Created by yurich on 16.12.16.
 */
class RetrieveSpecification(
        val direction: Int = RetrieveSpecification.SpecOptions.DEPARTURE,
        val source: Int = RetrieveSpecification.SpecOptions.FROM_LOCAL_DB,
        val query: String = ""
) {
    companion object SpecOptions {
        // Retrieve from server or database
        val FROM_SERVER = 1
        val FROM_LOCAL_DB = 2

        // Departure or Arrival Stations
        val DEPARTURE = 4
        val ARRIVAL = 8
    }
}