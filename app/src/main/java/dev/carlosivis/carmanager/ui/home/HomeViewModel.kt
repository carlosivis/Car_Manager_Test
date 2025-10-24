package dev.carlosivis.carmanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.carlosivis.carmanager.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val navigation: HomeNavigation,
    private val carRepository: CarRepository
): ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        getCars()
    }

    fun dispatchAction(action: HomeViewAction){
        when(action){
            is HomeViewAction.GetCars -> getCars()
            is HomeViewAction.Navigate.ToAddCar -> navigation.navigateToAddCar()
            is HomeViewAction.Navigate.ToEditCar -> TODO()
            is HomeViewAction.DeleteCar -> TODO()
        }
    }

    private fun getCars(){
        viewModelScope.launch {
            _state.update { it.copy(cars = carRepository.getCars()) }
        }
    }

}
