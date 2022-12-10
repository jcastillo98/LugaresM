package com.example.lugares.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lugares.R
//import com.example.lugares.UpdatePropiedadFragmentArgs
import com.example.lugares.databinding.FragmentAddPropiedadBinding
import com.example.lugares.databinding.FragmentUpdatePropiedadBinding
import com.example.lugares.model.Propiedad
import com.example.lugares.viewmodel.HomeViewModel


class UpdatePropiedadFragment : Fragment() {

    //Recupera agumentos
    private val args by navArgs<UpdatePropiedadFragmentArgs>()

    private var _binding: FragmentUpdatePropiedadBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel :: class.java)
        _binding = FragmentUpdatePropiedadBinding.inflate(inflater,container,false)

        //Caragar los valores edit
        binding.etNombre.setText(args.propiedad.nombre)
        binding.etCorreoComida.setText(args.propiedad.correo)
        binding.etTelefono.setText(args.propiedad.telefono)
        binding.etWeb.setText(args.propiedad.web)

        binding.btUpdatePropiedad.setOnClickListener { updatePropiedad() }
        binding.btDeleteLugar.setOnClickListener { deletePropiedad() }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun updatePropiedad() {
       val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoComida.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if(nombre.isEmpty()){
         Toast.makeText(requireContext(),getString(R.string.msg_error),Toast.LENGTH_LONG).show()
        }
        else if(correo.isEmpty()){
            Toast.makeText(requireContext(),getString(R.string.msg_error),Toast.LENGTH_LONG).show()
        }
        else{
            val propiedad = Propiedad(args.propiedad.id,nombre,correo,telefono,web)
            homeViewModel.savePropiedad(propiedad)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_updated),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updatePropiedadFragment_to_nav_home)
        }
    }

    private fun deletePropiedad() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.bt_borrar_propiedad))
        builder.setMessage(getString(R.string.msg_seguro_borrado)+" ${args.propiedad.nombre}?")
        builder.setNegativeButton(getString(R.string.msg_no)) {_,_ -> }
        builder.setPositiveButton(getString(R.string.msg_si)) {_,_ ->
            homeViewModel.deletePropiedad(args.propiedad)
            Toast.makeText(requireContext(),
                getString(R.string.msg_propiedad_deleted),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatePropiedadFragment_to_nav_home)
        }

        builder.create().show()


    }
}
