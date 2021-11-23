package com.tlgbltcn.jetaxi.data.repository

import com.tlgbltcn.jetaxi.data.mapper.TaxisMapper
import com.tlgbltcn.jetaxi.data.remote.JeTaxiService
import com.tlgbltcn.jetaxi.di.IoDispatcher
import com.tlgbltcn.jetaxi.feature.map.TaxisState
import com.tlgbltcn.jetaxi.util.networkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class JeTaxiRepository @Inject constructor(
    private val service: JeTaxiService,
    private val mapper: TaxisMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getTaxis() = flow {
        networkHandler(
            onCall = {
                service.getTaxis()
            }, onSuccess = { data ->
                val mappedData = mapper.map(input = data)
                emit(TaxisState.Content(taxis = mappedData.poiList))
            }, onFailure = { code, message ->
                emit(TaxisState.Error(code = code, message = message))
            })

    }.flowOn(dispatcher)
}