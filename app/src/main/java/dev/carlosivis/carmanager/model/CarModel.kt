package dev.carlosivis.carmanager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CarModel(
    val id: Long,
    val name: String,
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val lastestRevision: List<String>,
    val nextRevision: List<String>
) : Parcelable
