package dev.carlosivis.carmanager.navigation


import androidx.navigation.NavType

private const val ARG_CAR_ID = "carId"

object CarRoutes : Routes {
    override val branch = BranchDestination.Main

    object Home : LeafDestination(root = branch, route = "home")

    object CreateCar : LeafDestination(root = branch, route = "create_car")

    object Details : LeafDestination(
        root = branch,
        route = "details",
        args = listOf(
            NavArg(id = ARG_CAR_ID, type = NavType.StringType) // Usando String para ID do Firestore
        )
    ) {
        fun createRoute(carId: Long): String {
            return putArgs(ARG_CAR_ID to carId)
        }
    }

    //TODO: Criar rota para editar carro
    object EditCar : LeafDestination(
        root = branch,
        route = "edit_car",
        args = listOf(
            NavArg(id = ARG_CAR_ID, type = NavType.StringType) // Usando String para ID do Firestore
        )
    ) {
        // Helper para criar a rota com o ID
        fun createRoute(carId: Long): String {
            return putArgs(ARG_CAR_ID to carId)
        }
    }
}