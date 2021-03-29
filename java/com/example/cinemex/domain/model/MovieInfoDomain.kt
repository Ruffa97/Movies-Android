package com.example.cinemex.domain.model


data class MovieInfoDomain(
        val name: String,
        val label: String,
        val rating: String,
        val cover: String,
        val poster_big: String,
        val info: InfoMovieDomain
){
        fun getCoverUrl(): String{
                return ("https:" + cover.replace("\\", ""))
        }

        fun getCoverBigUrl(): String{
                return ("https:" + poster_big.replace("\\", ""))
        }
}