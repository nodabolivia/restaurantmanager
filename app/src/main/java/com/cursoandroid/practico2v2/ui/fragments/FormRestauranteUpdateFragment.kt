package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentFormRestauranteUpdateBinding
import com.example.gestorrestaurantes.dal.models.Restaurante

class FormRestauranteUpdateFragment : Fragment() {
    private var _binding: FragmentFormRestauranteUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private val args by navArgs<FormRestauranteUpdateFragmentArgs>()
    private lateinit var currentRestaurante: Restaurante


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormRestauranteUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        args.let {
            currentRestaurante = it.currentRestaurante
        }
        db = AppDatabase.getInstance(requireContext())
        loadDataContacto()
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener(){
        binding.btnActualizarRestaurante.setOnClickListener {
            updateDatatoDatabase()
        }
    }


    private fun loadDataContacto(){
//        currentRestaurante = args.currentRestaurante!!
        binding.txtNombreResUpdate.setText(currentRestaurante.nombre)
        binding.txtTelefonoResUpdate.setText(currentRestaurante.telefono)
    }
    private fun updateDatatoDatabase(){
//        currentRestaurante = args.currentRestaurante
//        val id = args.currentRestaurante.id
        val id = currentRestaurante.id
        val nombre= binding.txtNombreResUpdate.text.toString()
        val telefono = binding.txtTelefonoResUpdate.text.toString()
        if(inputCheckEmpty(nombre,telefono)){
            val restaurante = Restaurante(id,nombre = nombre, telefono = telefono)
            db.restauranteDao().update(restaurante)
            Toast.makeText(requireContext(),"Restaurante actualizado", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }else{
            Toast.makeText(requireContext(),"Rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheckEmpty(
        nombre:String,telefono:String
    ): Boolean{
        return !(TextUtils.isEmpty(nombre) && TextUtils.isEmpty(telefono) )
    }
}