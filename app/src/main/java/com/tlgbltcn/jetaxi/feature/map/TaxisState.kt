package com.tlgbltcn.jetaxi.feature.map

import com.tlgbltcn.jetaxi.domain.model.Taxis

sealed interface TaxisState {

    val isLoading: Boolean

    data class Content(
        val taxis: List<Taxis.Poi>? = listOf(), override val isLoading: Boolean = false
    ) : TaxisState

    data class Error(
        val code: Int?,
        val message: String?,
        override val isLoading: Boolean = false
    ) : TaxisState

}

data class TaxisViewModelState(
    val taxis: List<Taxis.Poi>? = null,
    val isLoading: Boolean = false,
    val code: Int? = null,
    val message: String? = null
) {

    fun toUiState(): TaxisState {
        return if (taxis.isNullOrEmpty().not()) {
            TaxisState.Content(
                taxis = taxis,
                isLoading = isLoading
            )
        } else {
            TaxisState.Error(
                isLoading = isLoading, code = code, message = message
            )
        }
    }
}


