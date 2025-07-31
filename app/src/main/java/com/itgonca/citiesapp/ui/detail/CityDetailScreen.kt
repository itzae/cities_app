package com.itgonca.citiesapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.ui.components.CardInformation
import com.itgonca.citiesapp.ui.components.TopAppBarCustom
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

@Composable
fun CityDetailScreen(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCustom(
                navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                onNavigate = onBack
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ImageHeader()
            InformationSection(modifier = Modifier.padding(vertical = CitiesAppTheme.dimens.paddingMedium))
            Text(
                modifier = Modifier.padding(CitiesAppTheme.dimens.paddingMedium),
                text = stringResource(R.string.city_detail_screen_description),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
private fun ImageHeader(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillBounds,
            model = "https://www.visit-mexico.mx/wp-content/uploads/2020/12/CDMX-Cover-01.jpg",
            contentDescription = ""
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(Color.Black.copy(0.4f))
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(CitiesAppTheme.dimens.paddingMedium), text = "Ciudad de México",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
    }
}

@Composable
private fun InformationSection(modifier: Modifier = Modifier) {
    LazyRow(modifier) {
        item {
            CardInformation(
                modifier = Modifier.padding(horizontal = CitiesAppTheme.dimens.paddingSmall),
                label = stringResource(R.string.city_detail_screen_elevation_label),
                information = stringResource(R.string.city_detail_screen_elevation_value)
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(horizontal = CitiesAppTheme.dimens.paddingSmall),
                label = stringResource(R.string.city_detail_screen_population_label),
                information = stringResource(R.string.city_detail_screen_population_value)
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(horizontal = CitiesAppTheme.dimens.paddingSmall),
                label = stringResource(R.string.city_detail_screen_zip_label),
                information = stringResource(R.string.city_detail_screen_zip_value)
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(horizontal = CitiesAppTheme.dimens.paddingSmall),
                label = stringResource(R.string.city_detail_screen_area_code_label),
                information = stringResource(R.string.city_detail_screen_area_code_value)
            )
        }

        item {
            CardInformation(
                modifier = Modifier.padding(horizontal = CitiesAppTheme.dimens.paddingSmall),
                label = stringResource(R.string.city_detail_screen_weather_label),
                information = stringResource(R.string.city_detail_screen_weather_value)
            )
        }

    }
}

@PreviewLightDark
@Composable
fun CityDetailScreenPreview() {
    CitiesAppTheme {
        CityDetailScreen()
    }
}