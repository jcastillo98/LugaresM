package com.example.lugares.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.lugares.data.LugarDatabase
import com.example.lugares.data.PropiedadDatabase
import com.example.lugares.model.Propiedad
import com.example.lugares.repository.LugarRepository
import com.example.lugares.repository.PropiedadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

   val obtenerPropiedades : LiveData<List<Propiedad>>
    private val repository: PropiedadRepository

    init {
        val PropiedadDao = PropiedadDatabase.getDatabase(application).propiedadDao()
        repository = PropiedadRepository(PropiedadDao)
        obtenerPropiedades = repository.obtenerPropiedad
    }

    fun savePropiedad (Propiedad: Propiedad) {
        viewModelScope.launch(Dispatchers.IO) { repository.guardarPropiedad(Propiedad)}
    }

    fun deletePropiedad (Propiedad: Propiedad) {
        viewModelScope.launch(Dispatchers.IO) {repository.eliminarPropiedad(Propiedad) }
    }
}