package com.binary.mobile.abstractions

import kotlinx.coroutines.flow.Flow

interface Repository

interface ReadRepository<ID, T> : Repository {
    suspend fun getById(id: ID): AppResult<T>
    fun observeById(id: ID): Flow<AppResult<T>>
}

interface WriteRepository<T> : Repository {
    suspend fun save(item: T): AppResult<Unit>
    suspend fun delete(item: T): AppResult<Unit>
}

interface CrudRepository<ID, T> : ReadRepository<ID, T>, WriteRepository<T> {
    fun observeAll(): Flow<AppResult<List<T>>>
    suspend fun deleteById(id: ID): AppResult<Unit>
}
