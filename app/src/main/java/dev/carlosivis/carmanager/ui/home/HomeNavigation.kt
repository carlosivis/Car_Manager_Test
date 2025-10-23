package dev.carlosivis.carmanager.ui.home

interface HomeNavigation {
    fun navigateToAddCar()
    fun navigateToDetails(carId: String)
    //TODO: Criar rota para editar carro
    fun navigateToEditCar(carId: String)
}