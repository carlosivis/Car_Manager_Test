package dev.carlosivis.carmanager.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class DetailsViewModel(): ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(DetailsViewState())
    val state = _state.asStateFlow()

    fun dispatchAction(action: DetailsViewAction){
        when(action){
            is DetailsViewAction.PopBackStack -> TODO()
        }

    }
}