package com.example.cinemex.domain.model

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


data class MovieDomain(
        var isShowingInfo: Boolean = false,
        val datetime: String,
        val timestamp: Long,
        val cinema: CinemaDomain,
        val id: Int,
        val tz_offset: Long,
        val movie: MovieInfoDomain
){
    private val currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + tz_offset
    private val timeLeft = ((timestamp + tz_offset)- currentTime) * 1000L

    fun oldMovie(): Boolean{
        return timeLeft < 0
    }

    fun minutesLeft(): Int{
        val minutesFormat = SimpleDateFormat("m", Locale.getDefault())
        minutesFormat.timeZone = TimeZone.getTimeZone("UTC")
        return minutesFormat.format(Date(timeLeft)).toInt()
    }

    fun hoursLeft(): Int{
        val hourFormat = SimpleDateFormat("h", Locale.getDefault())
        hourFormat.timeZone = TimeZone.getTimeZone("UTC")
        return hourFormat.format(Date(timeLeft)).toInt()
    }

    fun movieStart(): String{
        val minutesFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        minutesFormat.timeZone = TimeZone.getTimeZone("UTC")
        return minutesFormat.format(Date((timestamp + tz_offset)*1000L))
    }

}