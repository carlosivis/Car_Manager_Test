package dev.carlosivis.carmanager.ui.create

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.carlosivis.carmanager.R
import dev.carlosivis.carmanager.model.Brands
import dev.carlosivis.carmanager.ui.components.CustomDatePicker
import dev.carlosivis.carmanager.ui.theme.Dimens

@Composable
fun CreateScreen(viewModel: CreateCarViewModel) {
    val state by viewModel.state.collectAsState()
    val action = viewModel::dispatchAction

    Content(state = state, action = action)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun Content(
    state: CreateViewState,
    action: (CreateCarViewAction) -> Unit
) {
    AnimatedVisibility(state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens.Medium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.Medium)
            ) {
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
                            value = state.car.plate,
                            onValueChange = { action(CreateCarViewAction.OnCarChanged(state.car.copy(plate = it))) },
                            label = { Text(text = stringResource(id = R.string.plate_label)) },
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
                                    modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable ,enabled = true)
                                )

                                ExposedDropdownMenu(
                                    expanded = isExpanded,
                                    onDismissRequest = { isExpanded = false }
                                ) {
                                    Brands.carBrands.forEach { brand ->
                                        DropdownMenuItem(
                                            text = { Text(text = brand) },
                                            onClick = {
                                                action(CreateCarViewAction.OnCarChanged(state.car.copy(brand = brand)))
                                                isExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            OutlinedTextField(
                                value = state.car.model,
                                onValueChange = { action(CreateCarViewAction.OnCarChanged(state.car.copy(model = it))) },
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
                                    action(CreateCarViewAction.OnCarChanged(state.car.copy(year = year)))
                                },
                                label = { Text(text = stringResource(id = R.string.year_label)) },
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = state.car.color,
                                onValueChange = { action(CreateCarViewAction.OnCarChanged(state.car.copy(color = it))) },
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

                        // Last Revisions
                        Text(
                            text = "Últimas revisões",
                            style = MaterialTheme.typography.titleSmall
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Dimens.Small)
                        ) {
                            state.car.lastestRevision.forEach { date ->
                                InputChip(
                                    selected = false,
                                    onClick = { /* Nothing to do on click */ },
                                    label = { Text(date) },
                                    trailingIcon = {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Remove date",
                                            modifier = Modifier.clickable {
                                                val updatedList = state.car.lastestRevision - date
                                                action(CreateCarViewAction.OnCarChanged(state.car.copy(lastestRevision = updatedList)))
                                            }
                                        )
                                    }
                                )
                            }
                        }
                        Button(
                            onClick = { action(CreateCarViewAction.ShowDatePicker(DatePickerType.LAST_REVISION)) }
                        ) {
                            Text(stringResource(id = R.string.add_revision_button))
                        }
                        Spacer(modifier = Modifier.height(Dimens.Medium))

                        Box {
                            OutlinedTextField(
                                value = state.car.nextRevision.ifEmpty { stringResource(id = R.string.select_date_placeholder) },
                                onValueChange = {},
                                label = { Text(text = stringResource(id = R.string.next_revision_label)) },
                                readOnly = true,
                                trailingIcon = {
                                    Icon(Icons.Default.DateRange, contentDescription = stringResource(id = R.string.next_revision_label))
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable { action(CreateCarViewAction.ShowDatePicker(DatePickerType.NEXT_REVISION)) }
                            )
                        }
                    }
                }

                Button(
                    onClick = { action(CreateCarViewAction.OnClickSaveButton) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isValid
                ) {
                    Text(text = stringResource(id = R.string.save_button_label))
                }
            }

            AnimatedVisibility(state.showDatePicker) {
                CustomDatePicker(
                    onDateSelected = { date ->
                        when (state.datePickerType) {
                            DatePickerType.LAST_REVISION -> {
                                val updatedList = state.car.lastestRevision + date
                                action(CreateCarViewAction.OnCarChanged(state.car.copy(lastestRevision = updatedList)))
                            }
                            DatePickerType.NEXT_REVISION -> {
                                action(CreateCarViewAction.OnCarChanged(state.car.copy(nextRevision = date)))
                            }
                            DatePickerType.NONE -> Unit
                        }
                        action(CreateCarViewAction.HideDatePicker)
                    },
                    onDismiss = { action(CreateCarViewAction.HideDatePicker) }
                )
            }
        }
    }