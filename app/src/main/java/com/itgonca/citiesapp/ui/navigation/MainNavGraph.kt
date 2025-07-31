package com.itgonca.citiesapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itgonca.citiesapp.ui.detail.CityDetailScreen
import com.itgonca.citiesapp.ui.home.HomeScreenRoute
import com.itgonca.citiesapp.ui.map.CityMapScreen

@Composable
fun MainNavGraph(navHostController: NavHostController = rememberNavController()) {

    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            startDestination = ScreenRoutes.HomeScreen,
            navController = navHostController
        ) {
            composable<ScreenRoutes.HomeScreen> { HomeScreenRoute(navHostController = navHostController) }
            composable<ScreenRoutes.CityMapScreen> {
                val args: ScreenRoutes.CityMapScreen = it.toRoute()
                CityMapScreen(
                    name = args.name,
                    latitude = args.latitude,
                    longitude = args.longitude,
                    onShowDetail = { navHostController.navigate(ScreenRoutes.CityDetailScreen) },
                    onBack = { navHostController.navigateUp() })
            }
            composable<ScreenRoutes.CityDetailScreen> { CityDetailScreen(onBack = { navHostController.navigateUp() }) }
        }
    }

}