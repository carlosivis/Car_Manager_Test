package dev.carlosivis.carmanager


import android.app.Application
import dev.carlosivis.carmanager.di.carManagerAppModule // Importa o módulo
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(carManagerAppModule)
        }
    }
}