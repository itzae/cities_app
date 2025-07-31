package com.itgonca.citiesapp.ui.adaptive

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.ui.components.LoadingScreen
import com.itgonca.citiesapp.ui.detail.CityDetailScreen
import com.itgonca.citiesapp.ui.detail.CityDetailViewModel
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
fun AdaptiveScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    cityDetailViewModel: CityDetailViewModel = hiltViewModel()
) {

    val cities = viewModel.cities.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsStateWithLifecycle()
    var citySelected by remember { mutableStateOf<City?>(null) }
    val favoritesCitiesLazyItems: LazyPagingItems<City> =
        viewModel.favoriteCities.collectAsLazyPagingItems()
    val isShowFavorites by viewModel.isShowFavorites.collectAsStateWithLifecycle()
    var isShowDetail by remember { mutableStateOf(false) }
    val cityDetailState by cityDetailViewModel.weather.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(isShowDetail) {
        if (isShowDetail)
            cityDetailViewModel.fetchCityWeather(
                latitude = citySelected?.latitude ?: 0.0,
                longitude = citySelected?.latitude ?: 0.0
            )
    }

    Row(modifier = Modifier.fillMaxSize()) {
        if (!isShowDetail) {
            HomeScreen(
                modifier = Modifier.weight(1f),
                query = query,
                cities = if (isShowFavorites) favoritesCitiesLazyItems else cities,
                isShowFavorite = isShowFavorites,
                onShowMap = { citySelected = it },
                onSearch = { viewModel.onSearch(it) },
                onSelectFavorite = { id, isFavorite -> viewModel.onSelectFavorite(id, isFavorite) },
                onShowFavorites = { viewModel.onShowFavorites() }
            )
        }
        citySelected?.let {
            CityMapScreen(
                modifier = Modifier.weight(1f),
                name = "${it.name},${it.country}",
                latitude = it.latitude,
                longitude = it.longitude,
                onShowDetail = { isShowDetail = true },
                onBack = { if (citySelected != null) citySelected = null }
            )
        }

        if (isShowDetail) {
            when {
                cityDetailState.isLoading -> LoadingScreen(
                    modifier = Modifier.weight(1f),
                    message = stringResource(R.string.city_detail_loader_screen_message)
                )

                cityDetailState.isError -> {
                    Toast.makeText(
                        context,
                        stringResource(R.string.city_detail_screen_error_label),
                        Toast.LENGTH_SHORT
                    ).show()
                    isShowDetail = false
                }

                else ->
                    CityDetailScreen(
                        modifier = Modifier.weight(1f),
                        cityWeather = cityDetailState.weather,
                        onBack = { isShowDetail = false })
            }
        }
    }
}

@Preview
@Composable
private fun AdaptiveScreenPreview() {
    CitiesAppTheme { AdaptiveScreen() }
}