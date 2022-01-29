package com.example.gestorrestaurantes.dal.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "plato_table")
data class Plato(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val idRestaurante:Int,
    val idCategoria: Int,
    val nombre:String,
    val descripcion:String,
    val urlImg : Long
): Parcelable