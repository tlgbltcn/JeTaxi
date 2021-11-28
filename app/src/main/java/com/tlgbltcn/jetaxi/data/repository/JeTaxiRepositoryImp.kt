package com.tlgbltcn.jetaxi.data.repository

import com.tlgbltcn.jetaxi.data.mapper.TaxisMapper
import com.tlgbltcn.jetaxi.data.remote.JeTaxiService
import com.tlgbltcn.jetaxi.di.IoDispatcher
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.domain.repository.JeTaxiRepository
import com.tlgbltcn.jetaxi.util.ResultHolder
import com.tlgbltcn.jetaxi.util.networkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class JeTaxiRepositoryImp @Inject constructor(
    private val service: JeTaxiService,
    private val mapper: TaxisMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : JeTaxiRepository {

    override suspend fun getTaxis(): Flow<ResultHolder<Taxis>> = flow {
        networkHandler(
            onCall = {
                service.getTaxis()
            }, onSuccess = { data ->
                val mappedData = mapper.map(input = data)
                emit(ResultHolder.Success(mappedData))
            }, onFailure = { code, message, errorBody ->
                emit(ResultHolder.Error<Taxis>(message = message, code = code, data = errorBody))
            })
    }.flowOn(dispatcher)
}