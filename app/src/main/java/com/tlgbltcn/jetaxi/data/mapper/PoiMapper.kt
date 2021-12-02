package com.tlgbltcn.jetaxi.data.mapper

import com.tlgbltcn.jetaxi.data.model.TaxisDataModel
import com.tlgbltcn.jetaxi.domain.model.FleetType
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.util.ListMapper
import javax.inject.Inject

class PoiMapper @Inject constructor(private val coordinateMapper: CoordinateMapper) :
    ListMapper<TaxisDataModel.PoiDataModel, Taxis.Poi> {
    override fun map(input: List<TaxisDataModel.PoiDataModel>?): List<Taxis.Poi> {
        val poiList = mutableListOf<Taxis.Poi>()
        input?.forEach { poiDataModel ->
            poiList.add(
                Taxis.Poi(
                    coordinate = coordinateMapper.map(input = poiDataModel.coordinate),
                    fleetType = poiDataModel.fleetType ?: FleetType.TAXI,
                    heading = poiDataModel.heading ?: 123.46308138604739,
                    id = poiDataModel.id ?: -1
                )
            )
        }

        return poiList
    }
}