package com.itgonca.citiesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.citiesapp.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState>
        get() = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            _homeState.update { it.copy(isLoading = false, cities = cityRepository.getCities()) }
        }
    }

    fun onSearch(query: String) {
        cityRepository.searchCities(query)
            .onEach { response -> _homeState.update { it.copy(cities = response) } }
            .launchIn(viewModelScope)
    }

}