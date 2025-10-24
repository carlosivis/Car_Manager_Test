package dev.carlosivis.carmanager.di

import com.google.firebase.firestore.FirebaseFirestore
import dev.carlosivis.carmanager.navigation.CarNavigationImpl
import dev.carlosivis.carmanager.navigation.NavigationManager
import dev.carlosivis.carmanager.repository.CarRepository
import dev.carlosivis.carmanager.repository.CarRepositoryImpl
import dev.carlosivis.carmanager.ui.create.CreateCarNavigation
import dev.carlosivis.carmanager.ui.create.CreateCarViewModel
import dev.carlosivis.carmanager.ui.details.DetailsNavigation
import dev.carlosivis.carmanager.ui.details.DetailsViewModel
import dev.carlosivis.carmanager.ui.home.HomeNavigation
import dev.carlosivis.carmanager.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carManagerAppModule = module {

    single { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
    single { NavigationManager(get()) }

    single { FirebaseFirestore.getInstance() }
    single<CarRepository> { CarRepositoryImpl(get()) }

    single<HomeNavigation> { CarNavigationImpl(get()) }
    single<CreateCarNavigation> { CarNavigationImpl(get()) }
    single<DetailsNavigation> { CarNavigationImpl(get()) }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { CreateCarViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }

}
