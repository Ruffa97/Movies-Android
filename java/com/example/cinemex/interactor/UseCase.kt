package com.example.cinemex.interactor

import com.example.cinemex.domain.model.MovieResponseDomain
import com.example.cinemex.utils.Resource

interface UseCase {
    suspend fun fetchMovies(time: Int): Resource<MovieResponseDomain>
}