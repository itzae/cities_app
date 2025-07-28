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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.ui.components.TopAppBarCustom
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

@Composable
fun CityMapScreen(name: String, latitude: Double, longitude: Double, onBack: () -> Unit = {}) {
    val location = remember { LatLng(latitude, longitude) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }
    Scaffold(
        topBar = {
            TopAppBarCustom(
                title = name,
                navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                onNavigate = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
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