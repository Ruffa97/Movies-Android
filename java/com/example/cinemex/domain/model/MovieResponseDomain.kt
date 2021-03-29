package com.example.cinemex.domain.model

import com.example.cinemex.api.model.Movie

data class MovieResponseDomain(
        val movieList: List<MovieDomain>
)