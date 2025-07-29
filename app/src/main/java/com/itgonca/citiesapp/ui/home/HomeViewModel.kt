package com.itgonca.citiesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.itgonca.citiesapp.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {

    //Loader for data loading
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading.asStateFlow()

    //Search text
    private val _query = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query.asStateFlow()

    /*Stateflow to get the cities as soon as the composable subscribes, likewise tracks query changes
    to perform the search*/
    @OptIn(ExperimentalCoroutinesApi::class)
    val cities = query
        .flatMapLatest { text ->
            if (text.isBlank())
                cityRepository.getCities()
            else
                cityRepository.searchCities(text)
        }
        .onEach { _isLoading.update { false } }
        .cachedIn(viewModelScope)

    /**
     * This method allows you to updated the status of the search text
     * @param query is the new search text
     */
    fun onSearch(query: String) {
        _query.update { query }
    }

    /**
     * This method updates to the selected favorite city
     */
    fun onSelectFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch { cityRepository.updateFavoriteCity(id, isFavorite) }
    }

}