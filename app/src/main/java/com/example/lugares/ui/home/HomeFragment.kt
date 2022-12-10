package com.example.lugares.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lugares.R
import com.example.lugares.adapter.PropiedadAdapter
import com.example.lugares.databinding.FragmentHomeBinding
import com.example.lugares.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.addPropiedadFabBT.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_addPropiedad)
        }

        //Cargar Datos
        val propiedadAdapter = PropiedadAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = propiedadAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.obtenerPropiedades.observe(viewLifecycleOwner) {
                propiedades -> propiedadAdapter.setPropiedades(propiedades)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}