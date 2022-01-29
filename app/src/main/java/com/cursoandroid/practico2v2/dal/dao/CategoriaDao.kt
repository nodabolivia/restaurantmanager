package com.example.gestorrestaurantes.dal.dao

import androidx.room.*
import com.example.gestorrestaurantes.dal.models.*


@Dao
interface CategoriaDao {
//    @Query("SELECT * FROM categoria_table")
    @Query("SELECT id,nombre,idRestaurante FROM categoria_table")
    fun getAll():List<Categoria>

    @Query("SELECT * FROM categoria_table WHERE idRestaurante= :idRestaurante")
    fun getAllByRestaurante(idRestaurante: Int):List<Categoria>

    @Query("SELECT * FROM categoria_table WHERE id = :id")
    fun getById(id:Int): Categoria

    @Insert
    fun insert(vararg  categoria: Categoria)

    @Update
    fun update(categoria: Categoria)

    @Delete
    fun delete(categoria: Categoria)

    @Transaction
    @Query("SELECT * FROM categoria_table")
    fun getCategoriasWithPlatos():CategoriaWithPlatos

}