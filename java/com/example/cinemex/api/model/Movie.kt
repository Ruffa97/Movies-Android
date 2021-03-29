package com.example.cinemex.api.model

data class Movie(
        val datetime: String,
        val timestamp: Long,
        val auditorium_number: String,
        val cinema: Cinema,
        val availability: String,
        val id: Int,
        val tz_offset: Long,
        val movie: MovieInfo
)