package com.example.gestorrestaurantes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.practico2v2.databinding.CategoriaListItemBinding
import com.cursoandroid.practico2v2.ui.fragments.DetalleRestauranteFragmentDirections
import com.example.gestorrestaurantes.dal.models.Categoria

class CategoriaListAdapter
    (var listaCategorias: List<Categoria>) : RecyclerView.Adapter<CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        return CategoriaViewHolder(
            CategoriaListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val objCategoria = listaCategorias[position]
        holder.binding.itemCatNombre.text = "${objCategoria.nombre}"
        holder.binding.itemCatContainer.setOnClickListener {
            val action =
                DetalleRestauranteFragmentDirections.actionDetailCategoria(currentCategoria = objCategoria)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return this.listaCategorias.size
    }

    fun setData(listaCategorias: List<Categoria>) {
        this.listaCategorias = listaCategorias
    }
}

class CategoriaViewHolder(val binding: CategoriaListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}
