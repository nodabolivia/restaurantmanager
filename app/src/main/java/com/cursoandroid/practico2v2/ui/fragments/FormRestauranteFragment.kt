package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentFormRestauranteBinding
import com.example.gestorrestaurantes.dal.models.Restaurante
import com.google.android.material.snackbar.Snackbar


class FormRestauranteFragment : Fragment() {
    private lateinit var db: AppDatabase
    private var _binding: FragmentFormRestauranteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormRestauranteBinding.inflate(inflater, container, false)
        val view = binding.root

        db = AppDatabase.getInstance(requireContext())
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setupEventListener() {
        binding.btnAgregarRestaurante.setOnClickListener {
                insertDatatoDatabase()
        }


    }
    private fun insertDatatoDatabase(){
        val nombre= binding.txtNombreRes.text.toString()
        val telefono = binding.txtTelefonoRes.text.toString()

        if(inputCheckEmpty(nombre,telefono)){
            val restaurante = Restaurante(0,nombre = nombre,telefono = telefono)
            db.restauranteDao().insert(restaurante)
            Snackbar.make(binding.root.rootView,"Restaurante registrado", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            findNavController().popBackStack()

        }else{
            Snackbar.make(binding.root.rootView,"LLenar todos los campos", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun inputCheckEmpty(
        nombre:String,telefono:String
        ): Boolean{
        return !(TextUtils.isEmpty(nombre) &&TextUtils.isEmpty(telefono))
    }



}