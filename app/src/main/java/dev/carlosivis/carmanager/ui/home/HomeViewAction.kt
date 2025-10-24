package dev.carlosivis.carmanager.ui.home

sealed class HomeViewAction {

    object GetCars : HomeViewAction()
    object Navigate {
        object ToAddCar : HomeViewAction()
        data class ToEditCar(val carId: Long) : HomeViewAction()
    }
    data class DeleteCar(val carId: Long) : HomeViewAction()
}
