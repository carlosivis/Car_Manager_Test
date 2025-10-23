package dev.carlosivis.carmanager.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CarRoutes.branch.route,
        modifier = modifier.fillMaxSize()
    ) {
        addCarManagerGraph(navController)
    }
}