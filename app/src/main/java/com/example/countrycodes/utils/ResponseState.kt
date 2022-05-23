package com.example.countrycodes.utils

sealed class ResponseState {
    object Loading : ResponseState()
    class Success<T>(val response: T) : ResponseState()
    class Error(val error: Throwable) : ResponseState()
}