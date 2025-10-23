package dev.carlosivis.carmanager.ui.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.carlosivis.carmanager.R
import dev.carlosivis.carmanager.ui.theme.Dimens

@Composable
fun CreateScreen(viewModel: CreateViewModel) {
    val state by viewModel.state.collectAsState()
    val action = viewModel::dispatchAction

    Content(state = state, action = action)
}

@Composable
private fun Content(
    state: CreateViewState,
    action: (CreateViewAction) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.Medium)
    ) {
        OutlinedTextField(
            value = state.car.name,
            onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(name = it))) },
            label = { Text(text = stringResource(id = R.string.name_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.car.brand,
            onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(brand = it))) },
            label = { Text(text = stringResource(id = R.string.brand_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.car.model,
            onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(model = it))) },
            label = { Text(text = stringResource(id = R.string.model_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.car.color,
            onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(color = it))) },
            label = { Text(text = stringResource(id = R.string.color_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.car.nextRevision,
            onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(nextRevision = it))) },
            label = { Text(text = stringResource(id = R.string.next_revision_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { action(CreateViewAction.OnClickSaveButton) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.save_button_label))
        }
    }

}
