package com.maxi.news.ui.screens

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxi.news.ui.base.UiState
import com.maxi.news.ui.viewmodels.OptionsViewModel
import com.maxi.news.utils.Constants.COUNTRIES
import com.maxi.news.utils.Constants.COUNTRIES_PATH
import com.maxi.news.utils.Constants.HEADLINES_PATH
import com.maxi.news.utils.Constants.LANGUAGES
import com.maxi.news.utils.Constants.LANGUAGES_PATH
import com.maxi.news.utils.Constants.NEWS_SOURCES
import com.maxi.news.utils.Constants.NEWS_SOURCES_PATH
import com.maxi.news.utils.Constants.SEARCH
import com.maxi.news.utils.Constants.SEARCH_PATH
import com.maxi.news.utils.Constants.TOP_HEADLINES

@Composable
fun OptionsScreen(onOptionClicked: (option: String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val viewModel: OptionsViewModel = hiltViewModel()
        val state = viewModel.uiState.collectAsState()

        when (state.value) {
            is UiState.Success -> {
                val options = (state.value as UiState.Success).data
                OptionsList(
                    options,
                    onOptionClicked
                )
            }

            is UiState.Error -> {
                val error = (state.value as UiState.Error).error
                println("Error while fetching options $error")
            }

            is UiState.Loading -> {
                Loader()
            }
        }
    }
}

@Composable
fun OptionsList(options: List<String>, onOptionClicked: (option: String) -> Unit) {
    LazyColumn {
        items(options) { option ->
            OptionScreenItem(option, onOptionClicked)
        }
    }
}


@Composable
fun OptionScreenItem(option: String, onOptionClicked: (option: String) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clickable {
                onOptionClicked(mapOptionToPath(option))
            }
    ) {
        Text(
            modifier = Modifier
                .padding(12.dp),
            text = option,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

private fun mapOptionToPath(option: String): String {
    return when (option) {
        TOP_HEADLINES -> HEADLINES_PATH
        NEWS_SOURCES -> NEWS_SOURCES_PATH
        COUNTRIES -> COUNTRIES_PATH
        LANGUAGES -> LANGUAGES_PATH
        SEARCH -> SEARCH_PATH
        else -> ""
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOptionScreenItem() {
    OptionScreenItem("Top Headlines") {}
}

