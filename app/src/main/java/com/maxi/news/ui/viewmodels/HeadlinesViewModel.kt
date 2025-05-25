package com.maxi.news.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.news.domain.model.Article
import com.maxi.news.domain.usecase.HeadlinesUseCase
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
class HeadlinesViewModel @Inject constructor(
    private val useCase: HeadlinesUseCase,
    private val dispatcher: DispatcherProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        val source = savedStateHandle.get<String>("source") ?: ""
        getHeadlines(source)
    }

    private fun getHeadlines(source: String) {
        viewModelScope.launch {
            useCase(source)
                .flowOn(dispatcher.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }
                .collect { headlines ->
                    _uiState.value = UiState.Success(headlines)
                }
        }
    }
}