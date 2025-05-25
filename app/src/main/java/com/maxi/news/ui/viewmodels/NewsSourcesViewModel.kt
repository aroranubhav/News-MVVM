package com.maxi.news.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxi.news.domain.model.NewsSource
import com.maxi.news.domain.usecase.NewsSourcesUseCase
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
class NewsSourcesViewModel @Inject constructor(
    private val useCase: NewsSourcesUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsSource>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getNewsSources()
    }

    private fun getNewsSources() {
        viewModelScope.launch {
            useCase()
                .flowOn(dispatcher.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }
                .collect { sources ->
                    _uiState.value = UiState.Success(sources)
                }
        }
    }
}