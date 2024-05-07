package com.example.knownyc.presentation.parks

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knownyc.domain.apistate.ApiState
import com.example.knownyc.domain.repositories.NycParksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NycParksViewModel @Inject constructor(
    private val nycParksRepository: NycParksRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _parkListScreenState = MutableStateFlow<NycParksUIState>(NycParksUIState())
    val parkListScreenState = _parkListScreenState.asStateFlow()
    init {
        val borough = savedStateHandle.get<String>(key = "borough") ?: ""

        viewModelScope.launch {
            fetchParkList(borough = borough)
        }
    }

    private suspend fun fetchParkList(borough: String) {
        nycParksRepository.getParkList(borough = borough).collectLatest { apiState ->
            when (apiState) {
                is ApiState.Loading -> {
                    _parkListScreenState.value = _parkListScreenState.value.copy(isLoading = true)
                }
                is ApiState.Success -> {
                    _parkListScreenState.value = _parkListScreenState.value.copy(
                        parkList = apiState.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is ApiState.Error -> {
                    _parkListScreenState.value = _parkListScreenState.value.copy(
                        isLoading = false,
                        error = apiState.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}