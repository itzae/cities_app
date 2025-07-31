package com.itgonca.citiesapp.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.ui.components.TopAppBarCustom
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

/**
 * This composable displays a map with the location of the selected city
 * @param modifier allows screen configuration using attributes
 * @param name is the name of selected city
 * @param latitude is the latitude of selected city
 * @param longitude is the longitude of selected city
 * @param onBack is the action for navigation backwards
 */
@Composable
fun CityMapScreen(
    modifier: Modifier = Modifier,
    name: String,
    latitude: Double,
    longitude: Double,
    onShowDetail: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val location = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {

    }
    LaunchedEffect(location) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(location, 10f),
            durationMs = 1000
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCustom(
                title = name,
                navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                onNavigate = onBack
            )
        },
        floatingActionButton = {
            if (latitude == 19.428471 && longitude == -99.127663)
                FloatingActionButton(onClick = onShowDetail) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(R.string.city_map_detail_icon_cd)
                    )
                }
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { innerPadding ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = location),
                title = name,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CityMapScreenPreview() {
    CitiesAppTheme {
        CityMapScreen(name = "Example", latitude = 0.0, longitude = 0.0)
    }
}