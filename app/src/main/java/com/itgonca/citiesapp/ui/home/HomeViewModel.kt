package com.itgonca.citiesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itgonca.citiesapp.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val cities = query
        .flatMapLatest { text->
            if(text.isBlank())
                cityRepository.getCities()
            else
                cityRepository.searchCities(text)
        }
        .onEach { _isLoading.update { false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun onSearch(query: String) {
        _query.update { query }
    }

    fun onSelectFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch { cityRepository.updateFavoriteCity(id, isFavorite) }
    }

}