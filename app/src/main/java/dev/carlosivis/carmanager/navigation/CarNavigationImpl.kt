package dev.carlosivis.carmanager.navigation


import dev.carlosivis.carmanager.ui.home.HomeNavigation
import dev.carlosivis.carmanager.ui.create.CreateCarNavigation


internal class CarNavigationImpl(
    private val navManager: NavigationManager
) : HomeNavigation, CreateCarNavigation {

    override fun navigateToAddCar() {
        navManager.navigate(route = CarRoutes.CreateCar.createRoute())
    }

    override fun navigateToDetails(carId: Long) {
        navManager.navigate(route = CarRoutes.Details.createRoute(carId = carId))
    }

    override fun navigateToEditCar(carId: Long) {
        navManager.navigate(route = CarRoutes.EditCar.createRoute(carId = carId))
    }

    override fun popBackStack() {
        navManager.popBackStack()
    }

}