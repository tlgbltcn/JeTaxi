package com.tlgbltcn.jetaxi.data.model

import androidx.annotation.Keep
import com.tlgbltcn.jetaxi.domain.model.FleetType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TaxisDataModel(
    @SerialName("poiList")
    val poiList: List<PoiDataModel>? = null
) {
    @Keep
    @Serializable
    data class PoiDataModel(
        @SerialName("coordinate")
        val coordinate: CoordinateDataModel? = null,
        @SerialName("fleetType")
        val fleetType: FleetType? = null, // TAXI
        @SerialName("heading")
        val heading: Double? = null, // 123.46308138604739
        @SerialName("id")
        val id: Int? = null // 945294
    ) {
        @Keep
        @Serializable
        data class CoordinateDataModel(
            @SerialName("latitude")
            val latitude: Double? = null, // 53.44712321601976
            @SerialName("longitude")
            val longitude: Double? = null // 9.830312569773012
        )
    }
}
