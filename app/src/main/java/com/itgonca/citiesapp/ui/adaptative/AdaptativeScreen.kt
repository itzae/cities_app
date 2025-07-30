package com.itgonca.citiesapp.ui.adaptative

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.ui.home.HomeScreen
import com.itgonca.citiesapp.ui.home.HomeViewModel
import com.itgonca.citiesapp.ui.map.CityMapScreen
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

/**
 * This composable will take care of adapting the screens within the available space when the device
 * is in landscape mode
 * @param viewModel is the viewmodel of the home screen
 */
@Composable
fun AdaptativeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val cities = viewModel.cities.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsStateWithLifecycle()
    var citySelected by remember { mutableStateOf<City?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        HomeScreen(
            modifier = Modifier.weight(1f),
            query = query,
            cities = cities,
            onShowMap = { citySelected = it },
            onSearch = { viewModel.onSearch(it) })
        citySelected?.let {
            CityMapScreen(
                modifier = Modifier.weight(1f),
                name = "${it.name},${it.country}",
                latitude = it.latitude,
                longitude = it.longitude,
                onBack = { if (citySelected != null) citySelected = null }
            )
        }
    }
}

@Preview
@Composable
private fun AdaptativeScreenPreview() {
    CitiesAppTheme { AdaptativeScreen() }
}