package dev.carlosivis.carmanager.repository

import com.google.firebase.firestore.FirebaseFirestore
import dev.carlosivis.carmanager.model.CarModel
import kotlinx.coroutines.tasks.await

class CarRepositoryImpl(private val firestore: FirebaseFirestore) : CarRepository {

    override suspend fun getCars(): List<CarModel> {
        return try {
            firestore.collection("cars")
                .get()
                .await()
                .toObjects(CarModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addCar(car: CarModel) {
        firestore.collection("cars").add(car).await()
    }

    override suspend fun getCar(id: String): CarModel? {
        return try {
            firestore.collection("cars")
                .document(id)
                .get()
                .await()
                .toObject(CarModel::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
