package com.tlgbltcn.jetaxi.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlgbltcn.jetaxi.data.repository.JeTaxiRepository
import com.tlgbltcn.jetaxi.feature.map.TaxisState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: JeTaxiRepository
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
            val result: TaxisState = repository.getTaxis().single()
            viewModelState.update {
                when (result) {
                    is Content -> {
                        it.copy(taxis = result.taxis, isLoading = false)
                    }

                    is Error -> {
                        it.copy(exception = IllegalArgumentException(), isLoading = false)
                    }
                }
            }
        }
    }
}