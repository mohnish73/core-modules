package com.binary.mobile.abstractions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class UseCase<in P, out R>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(params: P): AppResult<R> = withContext(dispatcher) {
        runCatching { execute(params) }
            .fold(
                onSuccess = { AppResult.Success(it) },
                onFailure = { AppResult.Error(it.toAppException()) },
            )
    }

    protected abstract suspend fun execute(params: P): R
}

abstract class FlowUseCase<in P, out R>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    operator fun invoke(params: P): Flow<AppResult<R>> =
        execute(params)
            .catch { emit(AppResult.Error(it.toAppException())) }
            .flowOn(dispatcher)

    protected abstract fun execute(params: P): Flow<AppResult<R>>
}

abstract class NoParamUseCase<out R>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(): AppResult<R> = withContext(dispatcher) {
        runCatching { execute() }
            .fold(
                onSuccess = { AppResult.Success(it) },
                onFailure = { AppResult.Error(it.toAppException()) },
            )
    }

    protected abstract suspend fun execute(): R
}

private fun Throwable.toAppException(): AppException =
    if (this is AppException) this else AppException(message ?: "Unknown error", this)
