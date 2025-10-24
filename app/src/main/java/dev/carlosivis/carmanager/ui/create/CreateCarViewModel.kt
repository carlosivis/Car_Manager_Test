package dev.carlosivis.carmanager.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.carlosivis.carmanager.model.CarModel
import dev.carlosivis.carmanager.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CreateCarViewModel(
    private val navigation: CreateCarNavigation,
    private val carRepository: CarRepository
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(CreateViewState())
    val state = _state.asStateFlow()

    fun dispatchAction(action: CreateCarViewAction) {
        when (action) {
            is CreateCarViewAction.OnCarChanged -> onCarChanged(action.car)
            is CreateCarViewAction.OnClickSaveButton -> onClickSaveButton()
            is CreateCarViewAction.ShowDatePicker -> showDatePicker(action.type)
            is CreateCarViewAction.HideDatePicker -> hideDatePicker()
            is CreateCarViewAction.PopBackStack -> navigation.popBackStack()
        }
    }

    private fun onCarChanged(car: CarModel) {
        _state.update { it.copy(
                car = car,
                isValid = it.copy(car = car).validateFields()
            )
        }
    }

    private fun showDatePicker(type: DatePickerType) {
        _state.update { it.copy(
            showDatePicker = true,
            datePickerType = type
        ) }
    }

    private fun hideDatePicker() {
        _state.update { it.copy(
            showDatePicker = false,
            datePickerType = DatePickerType.NONE
        ) }
    }


    private fun onClickSaveButton() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }
                carRepository.addCar(state.value.car)

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
