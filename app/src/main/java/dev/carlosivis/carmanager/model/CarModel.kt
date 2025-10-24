package dev.carlosivis.carmanager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CarModel(
    val id: Long,
    val plate: String,
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val lastestRevision: List<String>,
    val nextRevision: String
) : Parcelable {
    constructor() : this(0, "", "", "", 0, "", emptyList(), "")
    companion object {
        fun empty() = CarModel(
            id = 0,
            plate = "",
            brand = "",
            model = "",
            year = 0,
            color = "",
            lastestRevision = emptyList(),
            nextRevision = ""
        )
    }
}
