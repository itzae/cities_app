package com.itgonca.citiesapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

@Composable
fun CardInformation(modifier: Modifier = Modifier, label: String, information: String) {
    Card(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(top = CitiesAppTheme.dimens.paddingSmall)
                .padding(horizontal = CitiesAppTheme.dimens.paddingSmall),
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            modifier = Modifier
                .padding(horizontal = CitiesAppTheme.dimens.paddingSmall)
                .padding(bottom = CitiesAppTheme.dimens.paddingSmall),
            text = information,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@PreviewLightDark
@Composable
private fun CardInformationPreview() {
    CitiesAppTheme { CardInformation(label = "Founded", information = "BC 800") }
}