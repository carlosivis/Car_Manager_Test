package dev.carlosivis.carmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold // Material 3
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.carlosivis.carmanager.navigation.AppNavigation // Importa o AppNavigation
import dev.carlosivis.carmanager.navigation.NavigationCommand
import dev.carlosivis.carmanager.navigation.NavigationManager
import dev.carlosivis.carmanager.navigation.NavigationType

import dev.carlosivis.carmanager.ui.theme.CarManagerTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    private val navManager: NavigationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarManagerTheme {
                // Usa Scaffold do Material 3
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )

                    LaunchedEffect(Unit) {
                        observeAndNavigate(navManager, navController)
                    }
                }
            }
        }
    }

    private fun observeAndNavigate(
        navManager: NavigationManager,
        navController: NavController
    ) {
        lifecycleScope.launch {
            navManager.commands.collectLatest { command ->
                when (command) {
                    is NavigationCommand.Navigate -> navigate(command, navController)
                    is NavigationCommand.NavigateUp -> navController.navigateUp()
                    is NavigationCommand.PopBackStack.Arguments -> popBackStackWithResult(
                        command,
                        navController
                    )
                    is NavigationCommand.PopBackStack.Default -> navController.popBackStack()
                }
            }
        }
    }

    private fun navigate(
        command: NavigationCommand.Navigate,
        navController: NavController
    ) {
        when (val type = command.type) {
            is NavigationType.NavigateTo -> navController.navigate(
                route = command.destination,
                navOptions = command.navOptions
            )
            is NavigationType.PopUpTo -> navController.popBackStack(
                route = command.destination,
                inclusive = type.inclusive
            )
        }
    }

    private fun popBackStackWithResult( // Renomeado para clareza
        command: NavigationCommand.PopBackStack.Arguments,
        navController: NavController
    ) {
        val targetRoute = command.route
        val backStackEntry = if (targetRoute != null) {
            navController.getBackStackEntry(targetRoute)
        } else {
            navController.previousBackStackEntry
        }

        backStackEntry?.savedStateHandle?.let { savedState ->
            for ((key, data) in command.value) {
                savedState[key] = data
            }
        }

        if (targetRoute != null) {
            navController.popBackStack(route = targetRoute, inclusive = false)
        } else {
            navController.popBackStack()
        }
    }
}