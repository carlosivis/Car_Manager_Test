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

    override suspend fun deleteCar(plate: String) {
        val query = firestore.collection("cars").whereEqualTo("plate", plate)
        val documents = query.get().await().documents
        if (documents.isNotEmpty()) {
            documents[0].reference.delete().await()
        }
    }
}
