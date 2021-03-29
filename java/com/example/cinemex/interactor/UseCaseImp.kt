package com.example.cinemex.interactor

import com.example.cinemex.api.repository.RemoteRepository.safeApiCall
import com.example.cinemex.api.service.CinemexApi
import com.example.cinemex.domain.model.MovieResponseDomain
import com.example.cinemex.utils.Resource
import javax.inject.Inject

class UseCaseImp @Inject constructor(private val cinemexApi: CinemexApi): UseCase {

    override suspend fun fetchMovies(time: Int): Resource<MovieResponseDomain> = safeApiCall {cinemexApi.getMovies(196,time,"full")}

}