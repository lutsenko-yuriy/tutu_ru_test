package com.example.yurich.tuturutest.repository

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.utils.display
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * This query will be used for creating an alert dialog
 * which is supposed to show chosen stations and date.
 *
 */

data class ResultQuery(
        var departureStation: StoragedStation? = null,
        var arrivalStation: StoragedStation? = null,
        var date: GregorianCalendar? = null
) {

    fun isReady(): Boolean {
        return departureStation != null && arrivalStation != null && date != null
    }

    fun getResultMessage(): String {
        return StringBuilder()
                .append("Из станции \"")
                .append("${departureStation!!.getFullAddress()}\"\n")
                .append("до станции \"")
                .append("${arrivalStation!!.getFullAddress()}\"\n")
                .append("на число ${date!!.display()}")
                .toString()
    }

    fun resetQuery() {
        departureStation = null
        arrivalStation = null
        date = null
    }
}