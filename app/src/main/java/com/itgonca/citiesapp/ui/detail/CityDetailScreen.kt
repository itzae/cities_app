package com.itgonca.citiesapp.ui.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.domain.model.CityWeather
import com.itgonca.citiesapp.ui.components.CardInformation
import com.itgonca.citiesapp.ui.components.LoadingScreen
import com.itgonca.citiesapp.ui.components.TopAppBarCustom
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

/**
 * This composable allow you to manage the state and navigation of the [CityDetailScreen]
 * @param viewModel This is the viewmodel of the home screen
 * @param navHostController This allows you to navigate the main screen
 */
@Composable
fun CityDetailScreenRoute(
    viewModel: CityDetailViewModel = hiltViewModel(),
    latitude: Double,
    longitude: Double,
    navHostController: NavHostController
) {

    val state by viewModel.weather.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchCityWeather(latitude = latitude, longitude = longitude)
    }

    when {
        state.isLoading -> LoadingScreen(
            modifier = Modifier.fillMaxSize(),
            message = stringResource(R.string.city_detail_loader_screen_message)
        )

        state.isError -> {
            Toast.makeText(
                context,
                stringResource(R.string.city_detail_screen_error_label),
                Toast.LENGTH_SHORT
            ).show()
            navHostController.navigateUp()
        }

        else -> CityDetailScreen(
            cityWeather = state.weather,
            onBack = { navHostController.navigateUp() })
    }
}

/**
 * This screen shows the weather of the selected city
 * @param modifier allows you to configure the screen
 * @param cityWeather is the object that contains the screen data
 * @param onBack is the action to navigate back
 */
@Composable
fun CityDetailScreen(
    modifier: Modifier = Modifier,
    cityWeather: CityWeather = CityWeather(),
    onBack: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCustom(
                title = "${cityWeather.cityName},${cityWeather.country}",
                navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                onNavigate = onBack
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ImageHeader(
                temperature = cityWeather.weather.temperature,
                description = cityWeather.weather.description,
                iconWeather = cityWeather.weather.iconWeather
            )
            InformationSection(
                modifier = Modifier
                    .padding(vertical = CitiesAppTheme.dimens.paddingMedium)
                    .height(500.dp),
                weatherInfo = cityWeather.weather
            )
        }
    }
}

@Composable
private fun ColumnScope.ImageHeader(
    modifier: Modifier = Modifier,
    temperature: Double,
    iconWeather: String,
    description: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${temperature}°", style = MaterialTheme.typography.displayLarge)
    }
    Row(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(
                start = CitiesAppTheme.dimens.paddingSmall,
                top = CitiesAppTheme.dimens.paddingSmall
            ),
            text = description,
            style = MaterialTheme.typography.titleLarge
        )
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .padding(end = CitiesAppTheme.dimens.paddingSmall),
            model = iconWeather,
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(R.string.city_detail_screen_icon_weather_cd)
        )
    }
}

@Composable
private fun InformationSection(modifier: Modifier = Modifier, weatherInfo: CityWeather.Weather) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_humidity_label),
                information = "${weatherInfo.humidity}%"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_temp_feel_label),
                information = "${weatherInfo.temperatureFeelLike}°"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_temp_min_label),
                information = "${weatherInfo.tempMin}°"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_temp_max_label),
                information = "${weatherInfo.tempMax}"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_wind_label),
                information = "${weatherInfo.wind}m/s"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_wind_direction_label),
                information = "${weatherInfo.windDirection}°"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_visibility_label),
                information = "${weatherInfo.visibility} m"
            )
        }
        item {
            CardInformation(
                modifier = Modifier.padding(8.dp),
                label = stringResource(R.string.city_detail_screen_clouds_label),
                information = "${weatherInfo.clouds}%"
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CityDetailScreenPreview() {
    CitiesAppTheme {
        CityDetailScreen(
            cityWeather = CityWeather(
                cityName = "Ciudad de México",
                country = "MX",
                weather = CityWeather.Weather(
                    description = "Nuboso",
                    iconWeather = "https://openweathermap.org/img/wn/04n@2x.png",
                    temperature = 17.0,
                    temperatureFeelLike = 12.0,
                    humidity = 24.0,
                    tempMin = 25.33,
                    tempMax = 25.44,
                    wind = 1.73,
                    windDirection = "65",
                    visibility = 1000.0,
                    clouds = 82.0
                ),
            )
        )
    }
}