package com.example.gestorrestaurantes.dal.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "categoria_table")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val idRestaurante:Int,
    val nombre:String
): Parcelable