package com.tlgbltcn.jetaxi.modules

import com.tlgbltcn.jetaxi.data.mapper.CoordinateMapper
import com.tlgbltcn.jetaxi.data.mapper.PoiMapper
import com.tlgbltcn.jetaxi.data.mapper.TaxisMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

    @Provides
    fun provideTaxisMapper(
        poiMapper: PoiMapper
    ): TaxisMapper = TaxisMapper(
        poiMapper = poiMapper
    )

    @Provides
    fun providePoiMapper(
        coordinateMapper: CoordinateMapper
    ): PoiMapper = PoiMapper(
        coordinateMapper = coordinateMapper
    )

    @Provides
    fun provideCoordinateMapper(): CoordinateMapper = CoordinateMapper()
}