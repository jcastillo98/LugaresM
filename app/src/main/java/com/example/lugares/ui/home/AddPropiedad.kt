package com.example.lugares.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddPropiedadBinding
import com.example.lugares.model.Propiedad
//import com.example.lugares.utilidades.AudioUtiles
//import com.example.lugares.utilidades.ImagenUtiles
import com.example.lugares.viewmodel.HomeViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class AddPropiedad : Fragment() {
 private var _binding: FragmentAddPropiedadBinding? = null
    private val binding get() = _binding!!
     private lateinit var  homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(homeViewModel::class.java)
        _binding = FragmentAddPropiedadBinding.inflate(inflater,container,false)

        binding.btAddPropiedad.setOnClickListener { agregarPropiedad() }

        // Inflate the layout for this fragment
        return binding.root
    }
    private fun agregarPropiedad() {
        val nombre=binding.etNombre.text.toString()
        val correo=binding.etCorreoComida.text.toString()
        val telefono=binding.etTelefono.text.toString()
        val web=binding.etWeb.text.toString()

        if (nombre.isNotEmpty()) {
            val propiedad = Propiedad(0,nombre,correo,telefono,web)
            //Proceso de agregar BD
            homeViewModel.savePropiedad(propiedad)
            Toast.makeText(requireContext(),"Exito",Toast.LENGTH_LONG).show()


        }
        else {
            Toast.makeText(requireContext(),getString(R.string.msg_error),Toast.LENGTH_LONG).show()
        }
    }
}
