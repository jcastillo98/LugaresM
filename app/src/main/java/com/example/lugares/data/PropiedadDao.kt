package com.example.lugares.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lugares.model.Propiedad

@Dao
interface PropiedadDao {
    //Obtener datos
    @Query("SELECT * FROM PROPIEDAD")
    fun obtenerPropiedades() : LiveData<List<Propiedad>>

    //Agregar datos
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarPropiedad(propiedad: Propiedad)

    //Actualizar datos
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun modificarPropiedad(propiedad: Propiedad)

    //Borrar datos
    @Delete
    suspend fun borrarPropiedad(propiedad: Propiedad)
}