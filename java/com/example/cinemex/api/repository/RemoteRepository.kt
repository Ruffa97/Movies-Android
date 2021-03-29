package com.example.cinemex.api.repository

import android.util.Log
import com.example.cinemex.BuildConfig
import com.example.cinemex.api.response.MovieResponse
import com.example.cinemex.api.response.MovieResponseMapper
import com.example.cinemex.domain.model.MovieResponseDomain
import com.example.cinemex.utils.Resource
import kotlinx.coroutines.CancellationException
import retrofit2.Response

object RemoteRepository {

    suspend fun safeApiCall(call: suspend () -> Response<MovieResponse>): Resource<MovieResponseDomain> {
        return try {
            newApiCall(call).run {
                return when (this) {
                    is Resource.Success -> this.info.let { Resource.Success(MovieResponseMapper.mapFromEntity(this.info)) }
                    is Resource.Failure -> Resource.Failure(this.msg, this.code)
                    is Resource.Error -> Resource.Error
                }
            }
        } catch (e: Exception) {
            if (e is CancellationException) {
                Resource.Failure(CANCELLATION, null)
            } else {
                if (BuildConfig.DEBUG) Log.e("API_CALL", e.localizedMessage, e)
                Resource.Error
            }
        }
    }

    private suspend fun <Model : Any> newApiCall(call: suspend () -> Response<Model>): Resource<Model> = call.invoke().let { response ->
        if (response.isSuccessful) {
            if (BuildConfig.DEBUG)
                Log.d("API_RESPONSE", Resource.Success(response.body()!!).toString())
            Resource.Success(response.body()!!)
        } else {
            val message = "FAILURE"
            val code = response.code()
            Resource.Failure(message, code)
        }
    }

    const val CANCELLATION = "cancellation"
}