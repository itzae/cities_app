package com.itgonca.citiesapp.ui.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.itgonca.citiesapp.testUtil.cityWeatherInformation
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class CityDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cityDetailScreen_VerifyInformation() {
        val cityDetail = cityWeatherInformation
        var isBack = false
        composeTestRule.setContent {
            CityDetailScreen(
                cityWeather = cityDetail,
                onBack = {isBack = true}
            )
        }
        composeTestRule.onNodeWithText("Ciudad de México,MX").assertExists()
        composeTestRule.onNodeWithText("17.0°").assertIsDisplayed()
        composeTestRule.onNodeWithText("Nuboso").assertExists()
        composeTestRule.onNodeWithText("Humedad").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Botón de navegación de barra superior").performClick()
        assertEquals(isBack,true)

    }

}