package com.tlgbltcn.jetaxi.feature.map

import com.tlgbltcn.jetaxi.ui.model.Taxis
import java.lang.Exception

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
    val exception: Exception? = null
) {

    fun toUiState(): TaxisState {
        return if (taxis.isNullOrEmpty().not()) {
            TaxisState.Content(
                taxis = taxis,
                isLoading = isLoading
            )
        } else {
            TaxisState.Error(
                isLoading = isLoading, code = null, message = null
            )
        }
    }
}


