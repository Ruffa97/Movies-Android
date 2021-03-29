package com.example.cinemex.api.model

data class InfoMovie(
        val duration: String,
        val year: String,
        val imdb_url: String,
        val director: String,
        val trailer: String,
        val genre: ArrayList<String>,
        val cast: String,
        val original_title: String,
        val sinopsis: String,
        val rating: String,
        val country: String
)