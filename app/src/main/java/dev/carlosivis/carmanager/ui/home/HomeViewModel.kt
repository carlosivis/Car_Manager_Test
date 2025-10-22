package dev.carlosivis.carmanager.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class HomeViewModel(): ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()


    fun dispatchAction(action: HomeViewAction){
        when(action){
            is HomeViewAction.GetCars -> getCars()
            is HomeViewAction.Navigate.ToAddCar -> TODO()
            is HomeViewAction.Navigate.ToDetailsCar -> TODO()
            is HomeViewAction.Navigate.ToEditCar -> TODO()
        }
    }

    private fun getCars(){

    }
}