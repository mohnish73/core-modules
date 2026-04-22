package com.binary.mobile.abstractions

sealed class AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>()
    data class Error(val exception: AppException) : AppResult<Nothing>()

    val isSuccess get() = this is Success
    val isError   get() = this is Error

    fun getOrNull(): T? = (this as? Success)?.data
    fun errorOrNull(): AppException? = (this as? Error)?.exception

    inline fun onSuccess(block: (T) -> Unit): AppResult<T> {
        if (this is Success) block(data)
        return this
    }

    inline fun onError(block: (AppException) -> Unit): AppResult<T> {
        if (this is Error) block(exception)
        return this
    }

    inline fun <R> map(transform: (T) -> R): AppResult<R> = when (this) {
        is Success -> Success(transform(data))
        is Error   -> this
    }
}

fun <T> T.asSuccess(): AppResult<T> = AppResult.Success(this)
fun appError(message: String, cause: Throwable? = null): AppResult<Nothing> =
    AppResult.Error(AppException(message, cause))
