package com.example.gestorrestaurantes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.databinding.RestauranteListItemBinding
import com.cursoandroid.practico2v2.ui.fragments.MainFragmentDirections
import com.example.gestorrestaurantes.dal.models.Restaurante

class RestauranteListAdapter(
    var listaRestaurantes:List<Restaurante>
) :
    RecyclerView.Adapter<RestauranteViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        return RestauranteViewHolder(
            RestauranteListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val objRestaurante = listaRestaurantes[position]
        holder.binding.itemResNombre.text = "${objRestaurante.nombre}"
//        TODO("FALTA LA FOTO DEL RESTAURANTE")
        holder.binding.itemResContainer.setOnClickListener({
            val action =
                MainFragmentDirections.actionDetailRestaurante(currentRestaurante = objRestaurante)
            holder.itemView.findNavController().navigate(action)
        })
    }

    override fun getItemCount(): Int {
       return this.listaRestaurantes.size
    }
    fun setData(listaRestaurantes: List<Restaurante>) {
        this.listaRestaurantes =listaRestaurantes
    }
}

class RestauranteViewHolder(val binding: RestauranteListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}
