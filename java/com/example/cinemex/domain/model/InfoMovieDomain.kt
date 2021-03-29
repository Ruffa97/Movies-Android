package com.example.cinemex.domain.model

class InfoMovieDomain(
        val sinopsis: String,
        val original_title: String,
        val rating: String,
        val country: String,
        val cast: String,
        val director: String,
        val trailer: String,
        val year: String,
        val genre: ArrayList<String>
) {
    fun getMovieId(): String{
        val startIndex = trailer.indexOf("=")
        return trailer.substring(startIndex + 1,startIndex + 12)
    }
}