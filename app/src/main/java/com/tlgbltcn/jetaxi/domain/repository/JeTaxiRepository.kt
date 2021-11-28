package com.tlgbltcn.jetaxi.domain.repository

import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.util.ResultHolder
import kotlinx.coroutines.flow.Flow

interface JeTaxiRepository {

    suspend fun getTaxis(): Flow<ResultHolder<Taxis>>
}