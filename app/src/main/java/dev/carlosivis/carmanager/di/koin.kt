package dev.carlosivis.carmanager.di

import dev.carlosivis.carmanager.navigation.NavigationManager

import dev.carlosivis.carmanager.navigation.CarNavigationImpl
import dev.carlosivis.carmanager.ui.create.CreateCarNavigation
import dev.carlosivis.carmanager.ui.create.CreateCarViewModel
import dev.carlosivis.carmanager.ui.details.DetailsNavigation
import dev.carlosivis.carmanager.ui.home.HomeNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import dev.carlosivis.carmanager.ui.details.DetailsViewModel
import dev.carlosivis.carmanager.ui.home.HomeViewModel


val carManagerAppModule = module {

    single { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
    single { NavigationManager(get()) }

    single<HomeNavigation> { CarNavigationImpl(get()) }
    single<CreateCarNavigation> { CarNavigationImpl(get()) }
    single<DetailsNavigation> { CarNavigationImpl(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { CreateCarViewModel(get()) }
    viewModel { DetailsViewModel(get()) }

}