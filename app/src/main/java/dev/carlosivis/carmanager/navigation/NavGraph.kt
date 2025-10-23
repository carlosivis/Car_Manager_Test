package dev.carlosivis.carmanager.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.carlosivis.carmanager.ui.create.CreateScreen
import dev.carlosivis.carmanager.ui.details.DetailsScreen
import dev.carlosivis.carmanager.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.addCarManagerGraph(navController: NavHostController) {
    navigation(
        route = CarRoutes.branch.route,
        startDestination = CarRoutes.Home.createRoute()
    ) {
        addHomeScreen(navController)
        addCreateCarScreen(navController)
        addDetailsScreen(navController)
    }
}

private fun NavGraphBuilder.addHomeScreen(navController: NavHostController) {
    composable(
        route = CarRoutes.Home.createRoute()
    ) {
        HomeScreen(viewModel = koinViewModel()) // Obtém ViewModel via Koin
    }
}

private fun NavGraphBuilder.addCreateCarScreen(navController: NavHostController) {
    composable(
        route = CarRoutes.CreateCar.createRoute()
    ) {
        CreateScreen(viewModel = koinViewModel()) // Obtém ViewModel via Koin
    }
}

private fun NavGraphBuilder.addDetailsScreen(navController: NavHostController) {
    composable(
        route = CarRoutes.Details.createRoute(),
        arguments = CarRoutes.Details.arguments // Define os argumentos esperados
    ) { backStackEntry ->
        val carId = backStackEntry.arguments?.getString("carId")
        // TODO: Adicionar tratamento se carId for nulo (não deveria acontecer se navegado corretamente)
        DetailsScreen(
            viewModel = koinViewModel(),
            carId = carId ?: "" // Passa o ID para a tela (ou ViewModel)
        )
    }
}

