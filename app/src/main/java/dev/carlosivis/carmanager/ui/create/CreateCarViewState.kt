package dev.carlosivis.carmanager.ui.create

import dev.carlosivis.carmanager.model.CarModel

data class CreateViewState(
    val error: Throwable? = null,
    val isLoading: Boolean = false,
    val car: CarModel = CarModel.empty(),
    val isValid: Boolean = false,
    val navigateBack: Boolean = false,
    val showDatePicker: Boolean = false,
    val datePickerType: DatePickerType = DatePickerType.NONE
)
enum class DatePickerType {
    LAST_REVISION,
    NEXT_REVISION,
    NONE
}

fun CreateViewState.validateFields(): Boolean {
    return car.plate.isNotBlank() &&
           car.brand.isNotBlank() &&
           car.model.isNotBlank() &&
           car.year > 1900 &&
           car.color.isNotBlank() &&
           car.nextRevision.isNotBlank()
}
