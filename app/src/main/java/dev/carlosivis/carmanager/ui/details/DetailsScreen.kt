package dev.carlosivis.carmanager.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, carId: String) {
    val state by viewModel.state.collectAsState()
    val action = viewModel::dispatchAction

    Content(state = state, action = action)
}

@Composable
private fun Content(
    state: DetailsViewState,
    action: (DetailsViewAction) -> Unit
){

}