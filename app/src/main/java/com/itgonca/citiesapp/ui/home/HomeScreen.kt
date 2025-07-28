package com.itgonca.citiesapp.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.ui.components.CityItem
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CitiesAppTheme.dimens.paddingMedium)
        )
    }) { innerPadding ->

        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(10) {
                CityItem(
                    modifier = Modifier.padding(
                        vertical = CitiesAppTheme.dimens.paddingSmall,
                        horizontal = CitiesAppTheme.dimens.paddingMedium
                    ),
                    title = "Arcelia, México",
                    subtitle = "10.20303,-100.21120",
                    isFavorite = false,
                    onItemClick = {},
                    onFavorite = {})
            }
        }
    }
}

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
        HomeScreen()
    }
}