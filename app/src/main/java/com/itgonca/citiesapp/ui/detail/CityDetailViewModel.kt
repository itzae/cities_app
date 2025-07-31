package com.itgonca.citiesapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.citiesapp.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityDetailViewModel @Inject constructor(private val cityRepository: CityRepository) :
    ViewModel() {
    //Stateflow that contains the state of the screen
    private val _weather = MutableStateFlow(CityDetailState())
    val weather: StateFlow<CityDetailState>
        get() = _weather.asStateFlow()

    /**
     * This function makes the request to the API to obtain the weather information of the selected city
     * @param latitude of the city
     * @param longitude of the city
     */
    fun fetchCityWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _weather.update { it.copy(isLoading = true) }
            try {
                val result =
                    cityRepository.getCityWeather(latitude = latitude, longitude = longitude)
                _weather.update { it.copy(isLoading = false, weather = result) }
            } catch (_: Exception) {
                _weather.update { it.copy(isLoading = false, isError = true) }
            }
        }

    }
}