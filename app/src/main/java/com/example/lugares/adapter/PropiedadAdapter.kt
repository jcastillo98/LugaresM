package com.example.lugares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lugares.databinding.FragmentPropiedadFilaBinding
import com.example.lugares.model.Propiedad
import com.example.lugares.ui.home.HomeFragmentDirections

class PropiedadAdapter: RecyclerView.Adapter<PropiedadAdapter.PropiedadViewHolder>() {

    private var  listaPropiedades = emptyList<Propiedad>()

    inner class PropiedadViewHolder(private val itemBinding: FragmentPropiedadFilaBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun dibujar(propiedad: Propiedad) {
            itemBinding.tvNombre.text = propiedad.nombre
            itemBinding.tvTelefono.text = propiedad.telefono
            itemBinding.tvCorreo.text = propiedad.correo

            //Evento edit
            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections
                    .actionNavHomeToUpdatePropiedadFragment(propiedad)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    fun setPropiedades(propiedades: List<Propiedad>){
        listaPropiedades = propiedades
        notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropiedadViewHolder {
        val itemBinding = FragmentPropiedadFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                ,parent,false)
        return PropiedadViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PropiedadViewHolder, position: Int) {
        val propiedad = listaPropiedades[position]
        holder.dibujar(propiedad)
    }

    override fun getItemCount(): Int {
        return listaPropiedades.size
    }

}

