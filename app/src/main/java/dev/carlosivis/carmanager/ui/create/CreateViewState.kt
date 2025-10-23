package dev.carlosivis.carmanager.ui.create

import dev.carlosivis.carmanager.model.CarModel

data class CreateViewState(
    val error: Throwable? = null,
    val isLoading: Boolean = false,
    val car: CarModel = CarModel.empty(),
    val isValid: Boolean = false,
    val navigateBack: Boolean = false
)

fun CreateViewState.validateFields(): Boolean {
    return car.name.isNotBlank() &&
           car.brand.isNotBlank() &&
           car.model.isNotBlank() &&
           car.year > 1900 &&
           car.color.isNotBlank() &&
           car.nextRevision.isNotBlank()
}
