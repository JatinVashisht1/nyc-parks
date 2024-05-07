package com.example.knownyc.domain.apistate

sealed class ApiState<T> (val data: T? = null, val message: String? = null) {
    class Loading<T>: ApiState<T>()
    class Success<T>(successData: T?) : ApiState<T>(data = successData)
    class Error<T>(errorMessage: String, data: T? = null) : ApiState<T>(message = errorMessage, data = data)
}