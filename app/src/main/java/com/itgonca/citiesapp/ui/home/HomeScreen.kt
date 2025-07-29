package com.itgonca.citiesapp.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.ui.components.CityItem
import com.itgonca.citiesapp.ui.components.LoadingScreen
import com.itgonca.citiesapp.ui.navigation.ScreenRoutes
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

/**
 * This composable allow you to manage the state and navigation of the [HomeScreen]
 * @param viewModel This is the viewmodel of the home screen
 */
@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    val cities by viewModel.cities.collectAsStateWithLifecycle()
    when {
        isLoading -> LoadingScreen()
        else -> HomeScreen(
            query = query,
            cities = cities,
            onShowMap = {
                navHostController.navigate(
                    ScreenRoutes.CityMapScreen(
                        id = it.id,
                        name = "${it.name},${it.country}",
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                )
            },
            onSearch = { viewModel.onSearch(it) },
            onSelectFavorite = { id, isFavorite -> viewModel.onSelectFavorite(id, isFavorite) }
        )
    }
}

/**
 * This composable is the main screen of the app.
 * @param query This parameter contains the text of the search
 */
@Composable
fun HomeScreen(
    query: String,
    cities: List<City> = emptyList(),
    onShowMap: (City) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onSelectFavorite: (Int, Boolean) -> Unit = { _, _ -> }
) {
    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CitiesAppTheme.dimens.paddingMedium),
                query = query,
                onQuery = { onSearch(it) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(cities, key = { it.id }) {
                CityItem(
                    modifier = Modifier.padding(
                        vertical = CitiesAppTheme.dimens.paddingSmall,
                        horizontal = CitiesAppTheme.dimens.paddingMedium
                    ),
                    title = "${it.name},${it.country}",
                    subtitle = "${it.latitude},${it.longitude}",
                    isFavorite = it.isFavorite,
                    onItemClick = { onShowMap(it) },
                    onFavorite = { onSelectFavorite(it.id, !it.isFavorite) })
            }
        }
    }
}

/**
 * This composable is the input to search for cities
 * @param modifier This parameter allows you to configure the composable
 */
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQuery: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        value = query,
        shape = RoundedCornerShape(CitiesAppTheme.dimens.cornerRadiusExtraLarge),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.home_cities_search_icon_cd)
            )
        },
        placeholder = { Text(text = stringResource(R.string.home_cities_searchbar_placeholder)) },
        onValueChange = onQuery
    )
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    CitiesAppTheme {
        HomeScreen(query = "", cities = List(10) {
            City(
                id = it,
                "Arcelia",
                "Mexico",
                latitude = 10.2340,
                longitude = -100.9458
            )
        })
    }
}