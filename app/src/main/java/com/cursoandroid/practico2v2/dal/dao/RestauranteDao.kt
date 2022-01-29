package com.example.gestorrestaurantes.dal.dao

import androidx.room.*
import com.example.gestorrestaurantes.dal.models.Restaurante
import com.example.gestorrestaurantes.dal.models.RestauranteWithCategorias
import com.example.gestorrestaurantes.dal.models.RestauranteWithPlatos


@Dao
interface RestauranteDao {
//    @Query("SELECT * FROM restaurante_table")
    @Query("SELECT id,nombre,telefono FROM restaurante_table")
    fun getAll():List<Restaurante>

    @Query("SELECT * FROM restaurante_table WHERE id = :id")
    fun getById(id:Int): Restaurante

    @Insert
    fun insert(vararg  restaurante: Restaurante)

    @Update
    fun update(restaurante: Restaurante)

    @Delete
    fun delete(restaurante: Restaurante)

    @Transaction
    @Query("SELECT * FROM restaurante_table")
    fun getRestauranteWithCategorias():List<RestauranteWithCategorias>

    @Transaction
    @Query("SELECT * FROM restaurante_table")
    fun getRestauranteWithPlatos():List<RestauranteWithPlatos>
}