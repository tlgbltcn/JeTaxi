package com.tlgbltcn.jetaxi.domain.mapper

import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import com.tlgbltcn.jetaxi.ui.model.Taxis
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