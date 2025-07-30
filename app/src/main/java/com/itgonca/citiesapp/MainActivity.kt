package com.itgonca.citiesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import com.itgonca.citiesapp.ui.adaptive.AdaptiveScreen
import com.itgonca.citiesapp.ui.navigation.MainNavGraph
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme
import com.itgonca.citiesapp.util.DeviceConfiguration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val deviceConfiguration = DeviceConfiguration.getWindowSizeClass(windowSizeClass)
            CitiesAppTheme {
                when (deviceConfiguration) {
                    DeviceConfiguration.MOBILE_PORTRAIT, DeviceConfiguration.TABLET_PORTRAIT -> MainNavGraph()
                    DeviceConfiguration.MOBILE_LANDSCAPE, DeviceConfiguration.TABLET_LANDSCAPE -> AdaptiveScreen()
                    DeviceConfiguration.OTHER -> Unit
                }
            }
        }
    }
}