package com.tlgbltcn.jetaxi.modules

import com.tlgbltcn.jetaxi.data.mapper.TaxisMapper
import com.tlgbltcn.jetaxi.data.remote.JeTaxiService
import com.tlgbltcn.jetaxi.data.repository.JeTaxiRepositoryImp
import com.tlgbltcn.jetaxi.domain.repository.JeTaxiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideJeTaxiRepository(
        service: JeTaxiService,
        mapper: TaxisMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): JeTaxiRepository = JeTaxiRepositoryImp(
        service = service,
        mapper = mapper,
        dispatcher = dispatcher
    )
}