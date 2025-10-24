package dev.carlosivis.carmanager.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
                    edit = { action(HomeViewAction.Navigate.ToEditCar(it.id)) },
                    delete = { action(HomeViewAction.DeleteCar(it.id)) }
                )
            }
        }
    }
}

@Composable
fun CarCard(
    car: CarModel,
    edit: () -> Unit,
    delete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(Shapes.ExtraLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.Small)
            .clickable { expanded = !expanded }
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.Large)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = car.plate + " - " + car.nextRevision,
                    modifier = Modifier.weight(1f),
                    fontSize = FontSizes.BodyLarge,
                )

                Row {
                    IconButton(onClick = edit) {
                        Icon(
                            Icons.Filled.Edit,
                            "Edit car"
                        )
                    }
                    IconButton(onClick = delete) {
                        Icon(
                            Icons.Filled.Delete,
                            "Delete car"
                        )
                    }
                }
            }

            AnimatedVisibility(expanded) {
                Spacer(modifier = Modifier.height(Dimens.Medium))
                Text(text = "Model: ${car.model}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(Dimens.Medium))
                Text(text = "Brand: ${car.brand}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(Dimens.Small))
                Text(text = "Year: ${car.year}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(Dimens.Small))
                Text(text = "Plate: ${car.plate}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(Dimens.Small))
                Text(text = "Next Revision: ${car.nextRevision}", style = MaterialTheme.typography.bodyLarge)
            }
        }

    }

}