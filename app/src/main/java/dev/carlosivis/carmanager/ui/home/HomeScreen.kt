package dev.carlosivis.carmanager.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(state.cars) {
                    CarCard(
                        car = it,
                        navigate = { action(HomeViewAction.Navigate.ToDetailsCar(it.id)) },
                        edit = { action(HomeViewAction.Navigate.ToEditCar(it.id)) },
                        delete = { action(HomeViewAction.DeleteCar(it.id)) })

                }
            }
        }
    }
}

@Composable
fun CarCard(
    car: CarModel, navigate: () -> Unit,
    edit: () -> Unit, delete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(Shapes.ExtraLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.Small)
            .clickable { navigate() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.Large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                car.plate + " - " + car.nextRevision,
                modifier = Modifier.weight(1f),
                fontSize = FontSizes.BodyLarge
            )

            Row {
                IconButton(onClick = edit) {
                    Icon(
                        Icons.Filled.Edit,
                        ""
                    )
                }
                IconButton(onClick = delete) {
                    Icon(
                        Icons.Filled.Delete,
                        ""
                    )
                }
            }
        }

    }

}


