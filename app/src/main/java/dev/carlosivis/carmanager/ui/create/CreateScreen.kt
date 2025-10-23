package dev.carlosivis.carmanager.ui.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.carlosivis.carmanager.R
import dev.carlosivis.carmanager.model.Brands
import dev.carlosivis.carmanager.ui.theme.Dimens

@Composable
fun CreateScreen(viewModel: CreateViewModel) {
    val state by viewModel.state.collectAsState()
    val action = viewModel::dispatchAction

    Content(state = state, action = action)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: CreateViewState,
    action: (CreateViewAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.Medium)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Medium),
                verticalArrangement = Arrangement.spacedBy(Dimens.Small)
            ) {
                Text(
                    text = stringResource(id = R.string.basic_info_title),
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = state.car.name,
                    onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(name = it))) },
                    label = { Text(text = stringResource(id = R.string.name_label)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.Small)
                ) {
                    var isExpanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = isExpanded,
                        onExpandedChange = { isExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = state.car.brand,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                            label = { Text(text = stringResource(id = R.string.brand_label)) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false }
                        ) {
                            Brands.carBrands.forEach { brand ->
                                DropdownMenuItem(
                                    text = { Text(text = brand) },
                                    onClick = {
                                        action(CreateViewAction.OnCarChanged(state.car.copy(brand = brand)))
                                        isExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = state.car.model,
                        onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(model = it))) },
                        label = { Text(text = stringResource(id = R.string.model_label)) },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.Small)
                ) {
                    OutlinedTextField(
                        value = state.car.year.toString(),
                        onValueChange = {
                            val year = it.toIntOrNull() ?: 0
                            action(CreateViewAction.OnCarChanged(state.car.copy(year = year)))
                        },
                        label = { Text(text = stringResource(id = R.string.year_label)) },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = state.car.color,
                        onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(color = it))) },
                        label = { Text(text = stringResource(id = R.string.color_label)) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Medium),
                verticalArrangement = Arrangement.spacedBy(Dimens.Small)
            ) {
                Text(
                    text = stringResource(id = R.string.maintenance_info_title),
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = state.car.nextRevision,
                    onValueChange = { action(CreateViewAction.OnCarChanged(state.car.copy(nextRevision = it))) },
                    label = { Text(text = stringResource(id = R.string.next_revision_label)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Button(
            onClick = { action(CreateViewAction.OnClickSaveButton) },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.isValid
        ) {
            Text(text = stringResource(id = R.string.save_button_label))
        }
    }
}
