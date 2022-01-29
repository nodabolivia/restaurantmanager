package com.example.gestorrestaurantes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.practico2v2.databinding.PlatoListItemBinding
import com.cursoandroid.practico2v2.ui.fragments.DetalleCategoriaFragmentDirections
import com.cursoandroid.practico2v2.ui.fragments.DetalleRestauranteFragmentDirections
import com.example.gestorrestaurantes.dal.models.Plato

class PlatoListAdapter(
    var listaPlatos: List<Plato>
) : RecyclerView.Adapter<PlatoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        return PlatoViewHolder(
            PlatoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val objPlato = listaPlatos[position]
        holder.binding.itemPlaNombre.text = "${objPlato.nombre}"
        holder.binding.itemPlaContainer.setOnClickListener {
            val action =
                DetalleCategoriaFragmentDirections.actionDetailPlato(currentPlato = objPlato)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return listaPlatos.size
    }

    fun setData(listaPlatos: List<Plato>) {
        this.listaPlatos = listaPlatos
    }
}

class PlatoViewHolder(val binding: PlatoListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}

