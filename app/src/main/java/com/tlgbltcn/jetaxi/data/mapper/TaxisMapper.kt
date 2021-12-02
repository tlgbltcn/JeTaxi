package com.tlgbltcn.jetaxi.data.mapper

import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.util.Mapper
import javax.inject.Inject

class TaxisMapper @Inject constructor(private val poiMapper: PoiMapper) :
    Mapper<TaxisDataModel, Taxis> {
    override fun map(input: TaxisDataModel?): Taxis {
        return Taxis(
            poiList = poiMapper.map(
                input = input?.poiList
            )
        )
    }
}