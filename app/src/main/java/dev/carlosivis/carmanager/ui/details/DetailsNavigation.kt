package dev.carlosivis.carmanager.ui.details


interface DetailsNavigation {
    fun popBackStack()
    //TODO: Criar rota para editar carro
    fun navigateToEditCar(carId: String)
}