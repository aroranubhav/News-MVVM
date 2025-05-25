package com.maxi.news.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxi.news.domain.model.NewsSource
import com.maxi.news.ui.base.UiState
import com.maxi.news.ui.viewmodels.NewsSourcesViewModel

@Composable
fun NewsSourcesScreen(onSourceItemClicked: (source: String) -> Unit) {
    val viewModel: NewsSourcesViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is UiState.Success -> {
            val sources = (state.value as UiState.Success).data
            NewsSourcesList(sources, onSourceItemClicked)
        }

        is UiState.Error -> {
            val error = (state.value as UiState.Error).error
            println("Error while fetching news sources $error")
        }

        is UiState.Loading -> {
            Loader()
        }
    }
}

@Composable
fun NewsSourcesList(sources: List<NewsSource>, onSourceItemClicked: (source: String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(sources) { source ->
                NewsSourceItem(source, onSourceItemClicked)
            }
        }
    }
}

@Composable
fun NewsSourceItem(source: NewsSource, onSourceItemClicked: (source: String) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { },
        border = BorderStroke(
            1.dp,
            Color.LightGray,
        ),
    ) {
        Text(
            text = source.name ?: "",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onSourceItemClicked(source.id ?: "")
                }
        )
    }
}