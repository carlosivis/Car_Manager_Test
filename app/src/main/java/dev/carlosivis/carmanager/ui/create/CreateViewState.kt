package dev.carlosivis.carmanager.ui.create

import dev.carlosivis.carmanager.model.CarModel

data class CreateViewState(
    val error: Throwable? = null,
    val isLoading: Boolean = false,
    val car: CarModel = CarModel.empty()
)
