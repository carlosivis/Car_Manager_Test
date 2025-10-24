package dev.carlosivis.carmanager.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.carlosivis.carmanager.ui.create.CreateScreen
import dev.carlosivis.carmanager.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.addCarManagerGraph(navController: NavHostController) {
    navigation(
        route = CarRoutes.branch.route,
        startDestination = CarRoutes.Home.createRoute()
    ) {
        addHomeScreen(navController)
        addCreateCarScreen(navController)
    }
}

private fun NavGraphBuilder.addHomeScreen(navController: NavHostController) {
    composable(
        route = CarRoutes.Home.createRoute()
    ) {
        HomeScreen(viewModel = koinViewModel())
    }
}

private fun NavGraphBuilder.addCreateCarScreen(navController: NavHostController) {
    composable(
        route = CarRoutes.CreateCar.createRoute()
    ) {
        CreateScreen(viewModel = koinViewModel())
    }
}


