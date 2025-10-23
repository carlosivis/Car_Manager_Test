package dev.carlosivis.carmanager.ui.create

import dev.carlosivis.carmanager.model.CarModel

sealed class CreateViewAction {
    object PopBackStack : CreateViewAction()
    data class OnCarChanged(val car: CarModel) : CreateViewAction()
    object OnClickSaveButton : CreateViewAction()
}
