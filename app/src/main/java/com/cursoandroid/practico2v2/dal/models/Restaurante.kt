package com.example.gestorrestaurantes.dal.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "restaurante_table")
data class Restaurante(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val nombre:String,
    val telefono:String

): Parcelable