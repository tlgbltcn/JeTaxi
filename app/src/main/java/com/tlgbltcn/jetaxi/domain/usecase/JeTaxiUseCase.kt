package com.tlgbltcn.jetaxi.domain.usecase

import com.tlgbltcn.jetaxi.data.repository.JeTaxiRepository
import com.tlgbltcn.jetaxi.feature.map.TaxisState
import com.tlgbltcn.jetaxi.ui.model.Taxis
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JeTaxiUseCase @Inject constructor(
    private val repository: JeTaxiRepository,
    // since no need a logic, no need dispatcher in this case.
    // @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Taxis, TaxisState> {

    override suspend fun execute(): Flow<TaxisState> {
        // no need a logic in this case.
        return repository.getTaxis()
    }
}