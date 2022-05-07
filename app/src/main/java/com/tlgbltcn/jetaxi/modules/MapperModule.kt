package com.tlgbltcn.jetaxi.modules

import com.tlgbltcn.jetaxi.data.mapper.CoordinateMapper
import com.tlgbltcn.jetaxi.data.mapper.PoiMapper
import com.tlgbltcn.jetaxi.data.mapper.TaxisMapper
import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.util.ListMapper
import com.tlgbltcn.jetaxi.util.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun provideCoordinateMapper(mapper: CoordinateMapper): Mapper<TaxisDataModel.PoiDataModel.CoordinateDataModel, Taxis.Poi.Coordinate>

    @Binds
    abstract fun providePoiMapper(listMapper: PoiMapper): ListMapper<TaxisDataModel.PoiDataModel, Taxis.Poi>

    @Binds
    abstract fun provideTaxiMapper(mapper: TaxisMapper): Mapper<TaxisDataModel, Taxis>
}