package com.tlgbltcn.jetaxi.domain.usecase

import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.domain.repository.JeTaxiRepository
import com.tlgbltcn.jetaxi.util.ResultHolder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JeTaxiUseCase @Inject constructor(
    private val repositoryImp: JeTaxiRepository,
    // since no need a logic, no need dispatcher in this case.
    // @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<ResultHolder<Taxis>> {

    override suspend fun execute(): Flow<ResultHolder<Taxis>> {
        return repositoryImp.getTaxis()
    }
}
