package com.tlgbltcn.jetaxi.domain.mapper

import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import com.tlgbltcn.jetaxi.ui.model.Taxis
import javax.inject.Inject

class CoordinateMapper @Inject constructor() :
    Mapper<TaxisDataModel.PoiDataModel.CoordinateDataModel,
            Taxis.Poi.Coordinate> {
    override fun map(input: TaxisDataModel.PoiDataModel.CoordinateDataModel?): Taxis.Poi.Coordinate {
        return Taxis.Poi.Coordinate(
            latitude = input?.latitude ?: 53.44712321601976,
            longitude = input?.longitude ?: 9.830312569773012
        )
    }
}