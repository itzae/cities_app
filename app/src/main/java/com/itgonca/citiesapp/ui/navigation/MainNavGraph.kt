package com.itgonca.citiesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itgonca.citiesapp.ui.home.HomeScreenRoute
import com.itgonca.citiesapp.ui.map.CityMapScreen

@Composable
fun MainNavGraph(navHostController: NavHostController = rememberNavController()) {

    NavHost(startDestination = ScreenRoutes.HomeScreen, navController = navHostController) {
        composable<ScreenRoutes.HomeScreen> { HomeScreenRoute(navHostController = navHostController) }
        composable<ScreenRoutes.CityMapScreen> {
            val args: ScreenRoutes.CityMapScreen = it.toRoute()
            CityMapScreen(
                name = args.name,
                latitude = args.latitude,
                longitude = args.longitude,
                onBack = { navHostController.navigateUp() })
        }
    }

}