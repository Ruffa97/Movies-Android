package com.example.cinemex.utils

sealed class Resource<out T: Any> {
    data class Success<out T: Any>(val info: T): Resource<T>()
    data class Failure(val msg: String?, val code: Int?): Resource<Nothing>()
    object Error: Resource<Nothing>()
}