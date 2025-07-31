package com.itgonca.citiesapp.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.itgonca.citiesapp.testUtil.cities
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_typeInputSearchField() {
        var text = ""
        composeTestRule.setContent {
            val citiesLazy = flowOf(PagingData.from(cities)).collectAsLazyPagingItems()

            HomeScreen(query = text, cities = citiesLazy, onSearch = { text = it })
        }
        composeTestRule.onNodeWithTag("search_field").performTextInput("Albuquerque")
        assertEquals(text, "Albuquerque")
    }

    @Test
    fun homeScreen_selectUnselectAllFavoriteCities() {
        var isFavorite = false
        composeTestRule.setContent {
            val citiesLazy = flowOf(PagingData.from(cities)).collectAsLazyPagingItems()

            HomeScreen(
                query = "",
                isShowFavorite = isFavorite,
                cities = citiesLazy,
                onShowFavorites = { isFavorite = !isFavorite })
        }
        composeTestRule.onNodeWithTag("favorite_button").performClick()
        assertEquals(true, isFavorite)
        composeTestRule.onNodeWithTag("favorite_button").performClick()
        assertEquals(false, isFavorite)
    }

    @Test
    fun homeScreen_selectUnselectFavoriteCity() {
        var isFavorite = false
        var id = 0
        composeTestRule.setContent {
            val citiesLazy = flowOf(PagingData.from(cities)).collectAsLazyPagingItems()

            HomeScreen(
                query = "",
                cities = citiesLazy,
                onSelectFavorite = { cityId, isFavoriteCity ->
                    isFavorite = isFavoriteCity
                    id = cityId
                }
            )
        }
        composeTestRule.onNodeWithTag("favorite_city_button_101").performClick()
        assertEquals(true, isFavorite)
        assertEquals(101, id)
    }
}