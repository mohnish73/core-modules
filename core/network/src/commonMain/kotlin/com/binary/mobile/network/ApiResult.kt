package com.binary.mobile.network

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val code: Int, val message: String) : ApiResult<Nothing>()
    data class Exception(val throwable: Throwable) : ApiResult<Nothing>()
}

inline fun <T> ApiResult<T>.onSuccess(block: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) block(data)
    return this
}

inline fun <T> ApiResult<T>.onError(block: (code: Int, message: String) -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) block(code, message)
    return this
}

inline fun <T> ApiResult<T>.onException(block: (Throwable) -> Unit): ApiResult<T> {
    if (this is ApiResult.Exception) block(throwable)
    return this
}
