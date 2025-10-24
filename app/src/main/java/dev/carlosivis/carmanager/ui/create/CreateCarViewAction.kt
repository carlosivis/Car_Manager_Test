package dev.carlosivis.carmanager.ui.create

import dev.carlosivis.carmanager.model.CarModel

sealed class CreateCarViewAction {
    object PopBackStack : CreateCarViewAction()
    data class OnCarChanged(val car: CarModel) : CreateCarViewAction()
    object OnClickSaveButton : CreateCarViewAction()
    data class ShowDatePicker(val type: DatePickerType) : CreateCarViewAction()
    object HideDatePicker : CreateCarViewAction()
}
