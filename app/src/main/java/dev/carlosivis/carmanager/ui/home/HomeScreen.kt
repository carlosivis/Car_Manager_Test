package dev.carlosivis.carmanager.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.carlosivis.carmanager.model.CarModel
import dev.carlosivis.carmanager.ui.theme.Dimens
import dev.carlosivis.carmanager.ui.theme.FontSizes
import dev.carlosivis.carmanager.ui.theme.Shapes


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()
    val action = viewModel::dispatchAction

    Content(state = state, action = action)
    LaunchedEffect(Unit) {
        action(HomeViewAction.GetCars)
    }

}

@Composable
private fun Content(
    state: HomeViewState,
    action: (HomeViewAction) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { action(HomeViewAction.Navigate.ToAddCar) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add car")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimens.Large)
        ) {
            items(state.cars) {
                CarCard(
                    car = it,
                    //edit = { action(HomeViewAction.Navigate.ToEditCar(it.id)) },
                    delete = { action(HomeViewAction.DeleteCar(it.plate)) }
                )
            }
        }
    }
}

@Composable
fun CarCard(
    car: CarModel,
    //edit: () -> Unit,
    delete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirmar exclusão") },
            text = { Text("Você tem certeza que deseja excluir este carro?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        delete()
                        showDeleteDialog = false
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        shape = RoundedCornerShape(Shapes.ExtraLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.Small)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .animateContentSize()
                .padding(horizontal = Dimens.Large, vertical = Dimens.Medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = car.plate,
                        fontSize = FontSizes.BodyLarge,
                    )
                    Spacer(modifier = Modifier.height(Dimens.ExtraSmall))
                    Text(
                        text = "Próxima revisão: ${car.nextRevision}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }

                Row {
                    //TODO: add edit config
//                    IconButton(onClick = edit) {
//                        Icon(
//                            Icons.Filled.Edit,
//                            contentDescription = "Edit car"
//                        )
//                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete car"
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.Medium)
                ) {
                    Text(text = "Modelo: " + car.model)
                    Spacer(modifier = Modifier.height(Dimens.Small))
                    Text(text = "Marca: " + car.brand)
                    Spacer(modifier = Modifier.height(Dimens.Small))
                    Text(text = "Ano: " + car.year.toString())
                    Spacer(modifier = Modifier.height(Dimens.Small))
                    Text(text = "Placa: " + car.plate)
                    Spacer(modifier = Modifier.height(Dimens.Small))
                    Text(text = "Últimas Revisões:")
                    Spacer(modifier = Modifier.height(Dimens.ExtraSmall))
                    car.lastestRevision.forEach { revision ->
                        Text(
                            text = "• $revision",
                            modifier = Modifier.padding(start = Dimens.Small)
                        )
                    }
                }
            }
        }

    }

}