package com.example.gestorrestaurantes.dal.dao

import androidx.room.*
import com.example.gestorrestaurantes.dal.models.*


@Dao
interface PlatoDao {
    @Query("SELECT id,nombre,descripcion,idRestaurante,idCategoria,urlImg FROM plato_table")
    fun getAll():List<Plato>

    @Query("SELECT * FROM plato_table WHERE idRestaurante= :idRestaurante")
    fun getAllByRestaurante(idRestaurante: Int):List<Plato>

    @Query("SELECT * FROM plato_table WHERE idCategoria= :idCategoria")
    fun getAllByCategoria(idCategoria: Int):List<Plato>

    @Query("SELECT * FROM plato_table WHERE id = :id")
    fun getById(id:Int): Plato

    @Insert
    fun insert(vararg  plato: Plato)

    @Update
    fun update(plato: Plato)

    @Delete
    fun delete(plato: Plato)


}