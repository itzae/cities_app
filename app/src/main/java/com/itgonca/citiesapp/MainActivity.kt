package com.itgonca.citiesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.itgonca.citiesapp.ui.home.HomeScreenRoute
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CitiesAppTheme { HomeScreenRoute() }
        }
    }
}