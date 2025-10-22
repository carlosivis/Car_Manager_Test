package dev.carlosivis.carmanager.ui.home

sealed class HomeViewAction {

    object GetCars : HomeViewAction()
    object Navigate {
        object ToAddCar : HomeViewAction()
        object ToEditCar : HomeViewAction()
        data class ToDetailsCar(val carId: Long) : HomeViewAction()
    }
}