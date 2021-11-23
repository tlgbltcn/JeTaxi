package com.tlgbltcn.jetaxi.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<T : Any, R> {
    suspend fun execute(): Flow<R>
}