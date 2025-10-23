package dev.carlosivis.carmanager.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.carlosivis.carmanager.model.CarModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CreateViewModel : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(CreateViewState())
    val state = _state.asStateFlow()

    fun dispatchAction(action: CreateViewAction) {
        when (action) {
            is CreateViewAction.OnCarChanged -> onCarChanged(action.car)
            is CreateViewAction.OnClickSaveButton -> onClickSaveButton()
            is CreateViewAction.PopBackStack -> { /* TODO */ }
        }
    }

    private fun onCarChanged(car: CarModel) {
        _state.update { it.copy(car = car) }
    }

    private fun onClickSaveButton() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // TODO: Save car logic here
            _state.update { it.copy(isLoading = false) }
        }
    }
}
