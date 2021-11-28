package com.tlgbltcn.jetaxi.presentation.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.domain.usecase.JeTaxiUseCase
import com.tlgbltcn.jetaxi.util.ResultHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val useCase: JeTaxiUseCase
) : ViewModel() {

    private val viewModelState = MutableStateFlow(TaxisViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        getTaxis()
    }

    private fun getTaxis() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            useCase.execute().single().run(this@MapViewModel::handleGetTaxiResult)
        }
    }

    private fun handleGetTaxiResult(result: ResultHolder<Taxis>) {
        viewModelState.update {
            when (result) {
                is ResultHolder.Success -> {
                    it.copy(
                        taxis = result.data.poiList,
                        isLoading = false
                    )
                }

                is ResultHolder.Error -> {
                    it.copy(
                        code = it.code,
                        message = it.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}