package com.example.cinemex.api.service

import com.example.cinemex.BuildConfig
import com.example.cinemex.api.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CinemexApi {
    @Headers("X-API-Consumer-Key: ${BuildConfig.API_CONSUMER_KEY}")
    @GET("rest/${BuildConfig.API_VERSION}/sessions/now/nearCinema/{id}")
    suspend fun getMovies(
            @Path("id") id: Int,
            @Query("time_limit") time: Int,
            @Query("movie_info") info: String
    ): Response<MovieResponse>
}