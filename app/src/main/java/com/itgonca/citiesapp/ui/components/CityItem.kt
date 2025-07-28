package com.itgonca.citiesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    isFavorite: Boolean,
    onItemClick: () -> Unit = {},
    onFavorite: () -> Unit = {}
) {
    Card(modifier = modifier, onClick = onItemClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CitiesAppTheme.dimens.paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = title, style = MaterialTheme.typography.titleSmall)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onFavorite) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.city_item_favorite_button_cd)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CityItemPreview() {
    CitiesAppTheme {
        CityItem(
            title = "Arcelia,México",
            subtitle = "10.20303,-100.21120",
            isFavorite = true
        )
    }
}