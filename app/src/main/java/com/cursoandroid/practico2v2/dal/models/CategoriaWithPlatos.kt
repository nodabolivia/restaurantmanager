package com.example.gestorrestaurantes.dal.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriaWithPlatos (
    @Embedded val Categoria: Categoria,
    @Relation(
        parentColumn = "id",
        entityColumn = "idCategoria"
    )
    val platos: List<Plato>
) : Parcelable