package com.cursoandroid.practico2v2.dal.conn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorrestaurantes.dal.dao.CategoriaDao
import com.example.gestorrestaurantes.dal.dao.PlatoDao
import com.example.gestorrestaurantes.dal.dao.RestauranteDao
import com.example.gestorrestaurantes.dal.models.Categoria
import com.example.gestorrestaurantes.dal.models.Plato
import com.example.gestorrestaurantes.dal.models.Restaurante


@Database(entities = [Restaurante::class, Plato::class, Categoria::class ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restauranteDao(): RestauranteDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun platoDao(): PlatoDao
    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "practicomoviles"
                ).allowMainThreadQueries()
                    .build()
            }
            return  INSTANCE!!
        }
    }
}

