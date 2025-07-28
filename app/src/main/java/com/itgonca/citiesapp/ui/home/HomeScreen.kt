package com.itgonca.citiesapp.ui.home

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
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.ui.components.CityItem
import com.itgonca.citiesapp.ui.components.LoadingScreen
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

/**
 * This composable allow you to manage the state and navigation of the [HomeScreen]
 * @param viewModel This is the viewmodel of the home screen
 */
@Composable
fun HomeScreenRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.homeState.collectAsStateWithLifecycle()
    when {
        state.isLoading -> LoadingScreen()
        else -> HomeScreen(state = state.cities)
    }
}

/**
 * This composable is the main screen of the app.
 * @param state This parameter contains the state of the screen.
 */
@Composable
fun HomeScreen(state: List<City>) {
    Scaffold(topBar = {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CitiesAppTheme.dimens.paddingMedium)
        )
    }) { innerPadding ->

        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(state, key = { it.id }) {
                CityItem(
                    modifier = Modifier.padding(
                        vertical = CitiesAppTheme.dimens.paddingSmall,
                        horizontal = CitiesAppTheme.dimens.paddingMedium
                    ),
                    title = "${it.name},${it.country}",
                    subtitle = "${it.latitude}-${it.longitude}",
                    isFavorite = false,
                    onItemClick = {},
                    onFavorite = {})
            }
        }
    }
}

/**
 * This composable is the input to search for cities
 * @param modifier This parameter allows you to configure the composable
 */
@Composable
private fun SearchBar(modifier: Modifier = Modifier) {
    OutlinedTextField(
        modifier = modifier,
        value = "",
        shape = RoundedCornerShape(CitiesAppTheme.dimens.cornerRadiusExtraLarge),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.home_cities_search_icon_cd)
            )
        },
        placeholder = { Text(text = stringResource(R.string.home_cities_searchbar_placeholder)) },
        onValueChange = {})
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    CitiesAppTheme {
        HomeScreen(state = List(10) {
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