package com.example.lugares.repository

import androidx.lifecycle.LiveData
import com.example.lugares.data.PropiedadDao
import com.example.lugares.model.Propiedad

class PropiedadRepository (private val propiedadDao: PropiedadDao) {

    suspend fun  guardarPropiedad(propiedad: Propiedad){
        if(propiedad.id == 0){
            propiedadDao.agregarPropiedad(propiedad)
        }
        else{
            propiedadDao.modificarPropiedad(propiedad)
        }
    }
    suspend fun eliminarPropiedad(propiedad: Propiedad){
            propiedadDao.borrarPropiedad(propiedad)
    }

    val obtenerPropiedad: LiveData<List<Propiedad>> = propiedadDao.obtenerPropiedades()
}