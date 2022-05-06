package com.tlgbltcn.jetaxi.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<T> {
    suspend fun execute(): Flow<T>
}
