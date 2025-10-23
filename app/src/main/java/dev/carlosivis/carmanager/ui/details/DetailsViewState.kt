package dev.carlosivis.carmanager.ui.details

import dev.carlosivis.carmanager.model.CarModel

data class DetailsViewState(
    val car: CarModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)