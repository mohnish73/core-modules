package com.binary.mobile.abstractions

class AppException(
    message: String,
    cause: Throwable? = null,
    val code: Int = -1,
) : Exception(message, cause)
