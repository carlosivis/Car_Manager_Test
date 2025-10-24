package dev.carlosivis.carmanager.repository

import dev.carlosivis.carmanager.model.CarModel

interface CarRepository {
    suspend fun getCars(): List<CarModel>
    suspend fun addCar(car: CarModel)
}
