package com.maxi.news.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.news.domain.usecase.OptionsUseCase
import com.maxi.news.ui.base.UiState
import com.maxi.news.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val useCase: OptionsUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getOptions()
    }

    private fun getOptions() {
        viewModelScope.launch {
            useCase()
                .flowOn(dispatcher.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }
                .collect { options ->
                    _uiState.value = UiState.Success(options)
                }
        }
    }
}