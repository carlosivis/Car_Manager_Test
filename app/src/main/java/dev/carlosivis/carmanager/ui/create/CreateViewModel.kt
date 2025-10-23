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
            is CreateViewAction.PopBackStack -> TODO()
        }
    }

    private fun onCarChanged(car: CarModel) {
        _state.update { it.copy(
                car = car,
                isValid = it.copy(car = car).validateFields()
            )
        }
    }

    private fun onClickSaveButton() {
        viewModelScope.launch {
            try {
//                _state.update { it.copy(isLoading = true, error = null) }
//
//                // Validate year
//                val currentYear = LocalDate.now().year
//                if (state.value.car.year > currentYear) {
//                    throw IllegalArgumentException("O ano não pode ser maior que o ano atual")
//                }
//
//                // Validate date format for nextRevision
//                try {
//                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//                    LocalDate.parse(state.value.car.nextRevision, formatter)
//                } catch (e: Exception) {
//                    throw IllegalArgumentException("Data da próxima revisão inválida. Use o formato dd/mm/aaaa")
//                }

                // TODO: Add your repository call here to save the car
                // val savedCar = carRepository.saveCar(state.value.car)

                _state.update { it.copy(
                    isLoading = false,
                    navigateBack = true
                ) }
            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false,
                    error = e
                ) }
            }
        }
    }
}
