package com.maxi.news.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.maxi.news.domain.model.Article
import com.maxi.news.ui.base.UiState
import com.maxi.news.ui.viewmodels.HeadlinesViewModel

@Composable
fun HeadlinesScreen() {
    val viewModel: HeadlinesViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is UiState.Success -> {
            val headlines = (state.value as UiState.Success).data
            HeadlinesList(headlines)
        }

        is UiState.Error -> {
            val error = (state.value as UiState.Error).error
            println("Error while fetching headlines $error")
        }

        is UiState.Loading -> {
            Loader()
        }
    }
}

@Composable
fun HeadlinesList(headlines: List<Article>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(headlines) { headline ->
                HeadlineListItem(headline)
            }
        }
    }
}

@Composable
fun HeadlineListItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(
            1.dp,
            Color.LightGray
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            article.imageUrl?.let {
                SubcomposeAsyncImage(
                    model = it,
                    loading = {
                        Loader()
                    },
                    contentDescription = ""
                )
            }
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(0.8f)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(0.dp, 6.dp)
            )
            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(0.dp, 6.dp)
            )
            Text(
                text = article.url ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(0.dp, 6.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth(0.8f)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

    }
}