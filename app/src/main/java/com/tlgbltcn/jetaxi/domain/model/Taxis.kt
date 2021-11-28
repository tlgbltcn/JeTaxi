package com.tlgbltcn.jetaxi.domain.model


data class Taxis(
    val poiList: List<Poi>
) {
    data class Poi(
        val coordinate: Coordinate,
        val fleetType: FleetType, // TAXI
        val heading: Double, // 123.46308138604739
        val id: Int // 945294
    ) {
        data class Coordinate(
            val latitude: Double, // 53.44712321601976
            val longitude: Double // 9.830312569773012
        )
    }
}

enum class FleetType {
    POOLING,
    TAXI
}