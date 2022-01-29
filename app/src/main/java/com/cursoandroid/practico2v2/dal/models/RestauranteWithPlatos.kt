package com.example.gestorrestaurantes.dal.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestauranteWithPlatos(
    @Embedded val restaurante: Restaurante,
    @Relation(
        parentColumn = "id",
        entityColumn = "idRestaurante"
    )
    val platos: List<Plato>
) : Parcelable