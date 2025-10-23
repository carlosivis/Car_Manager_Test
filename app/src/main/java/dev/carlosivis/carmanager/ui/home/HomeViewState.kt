package dev.carlosivis.carmanager.ui.home

import dev.carlosivis.carmanager.model.CarModel

data class HomeViewState (
    val cars: List<CarModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)

