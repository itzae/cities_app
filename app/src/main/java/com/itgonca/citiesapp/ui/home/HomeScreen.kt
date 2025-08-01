package com.itgonca.citiesapp.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.ui.components.CityItem
import com.itgonca.citiesapp.ui.components.LoadingScreen
import com.itgonca.citiesapp.ui.navigation.ScreenRoutes
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme
import kotlinx.coroutines.flow.flowOf

/**
 * This composable allow you to manage the state and navigation of the [HomeScreen]
 * @param viewModel This is the viewmodel of the home screen
 * @param navHostController This allows you to navigate the main screen
 */
@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    val citiesLazyItems: LazyPagingItems<City> = viewModel.cities.collectAsLazyPagingItems()
    val favoritesCitiesLazyItems: LazyPagingItems<City> =
        viewModel.favoriteCities.collectAsLazyPagingItems()
    val isShowFavorites by viewModel.isShowFavorites.collectAsStateWithLifecycle()
    when {
        isLoading -> LoadingScreen(
            modifier = Modifier.fillMaxSize(),
            message = stringResource(R.string.home_screen_loader_message)
        )

        else -> HomeScreen(
            query = query,
            cities = if (isShowFavorites) favoritesCitiesLazyItems else citiesLazyItems,
            isShowFavorite = isShowFavorites,
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
            onSelectFavorite = { id, isFavorite -> viewModel.onSelectFavorite(id, isFavorite) },
            onShowFavorites = { viewModel.onShowFavorites() }
        )
    }
}

/**
 * This composable is the main screen of the app.
 * @param query This parameter contains the text of the search
 * @param cities It is the list of cities paginated for optimal loading within the composable
 * @param onShowMap Navigate to the screen that shows the map with the location of the city
 * @param onSearch Update the search text and  perform the city search
 * @param onSelectFavorite Select a city as a favorite and updated it
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    query: String,
    cities: LazyPagingItems<City>,
    isShowFavorite: Boolean = false,
    onShowMap: (City) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onSelectFavorite: (Int, Boolean) -> Unit = { _, _ -> },
    onShowFavorites: (Boolean) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CitiesAppTheme.dimens.paddingSmall),
                query = query,
                isFavoriteSelected = isShowFavorite,
                onQuery = { onSearch(it) },
                onShowFavorites = { onShowFavorites(true) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(
                cities.itemCount,
                key = cities.itemKey(City::id),
                contentType = { cities.itemContentType { "Cities" } }
            ) { index ->
                val city = cities[index] ?: return@items
                CityItem(
                    modifier = Modifier
                        .padding(
                            vertical = CitiesAppTheme.dimens.paddingSmall,
                            horizontal = CitiesAppTheme.dimens.paddingMedium
                        )
                        .testTag("city_card_${city.id}"),
                    id = city.id,
                    title = "${city.name},${city.country}",
                    subtitle = "${city.latitude},${city.longitude}",
                    isFavorite = city.isFavorite,
                    onItemClick = { onShowMap(city) },
                    onFavorite = { onSelectFavorite(city.id, !city.isFavorite) })
            }
        }
    }
}

/**
 * This composable is the input to search for cities
 * @param modifier This parameter allows you to configure the composable
 * @param query is the search text
 * @param onQuery is the action that contains the search text
 */
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    isFavoriteSelected: Boolean = false,
    onShowFavorites: () -> Unit,
    onQuery: (String) -> Unit = {}
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            modifier = modifier
                .weight(1f)
                .testTag("search_field"),
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
        IconButton(modifier = Modifier.testTag("favorite_button"), onClick = onShowFavorites) {
            Icon(
                imageVector = if (isFavoriteSelected) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.home_scree_favorites_button_cd)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    CitiesAppTheme {
        val items = List(10) {
            City(
                id = it,
                "Arcelia",
                "Mexico",
                latitude = 10.2340,
                longitude = -100.9458
            )
        }
        val pagingData = flowOf(PagingData.from(items)).collectAsLazyPagingItems()
        HomeScreen(query = "", cities = pagingData)
    }
}