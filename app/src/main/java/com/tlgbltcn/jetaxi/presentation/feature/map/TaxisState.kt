package com.tlgbltcn.jetaxi.presentation.feature.map

import com.tlgbltcn.jetaxi.domain.model.Taxis

sealed interface TaxisState {

    val isLoading: Boolean

    data class Content(
        val taxis: List<Taxis.Poi>? = listOf(),
        override val isLoading: Boolean = false
    ) : TaxisState

    data class Error(
        val code: Int?,
        val message: String?,
        override val isLoading: Boolean = false
    ) : TaxisState
}
