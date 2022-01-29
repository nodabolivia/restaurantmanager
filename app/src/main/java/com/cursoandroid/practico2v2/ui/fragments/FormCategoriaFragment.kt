package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentFormCategoriaBinding
import com.example.gestorrestaurantes.dal.models.Categoria
import com.example.gestorrestaurantes.dal.models.Restaurante
import com.google.android.material.snackbar.Snackbar


class FormCategoriaFragment : Fragment() {
    private val args by navArgs<FormCategoriaFragmentArgs>()
    private lateinit var db: AppDatabase
    private lateinit var currentRestaurante: Restaurante

    private var _binding: FragmentFormCategoriaBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormCategoriaBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentRestaurante = it.currentRestaurante
        }
        setupEventListener()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnAgregarCategoria.setOnClickListener {
            insertDatatoDatabase()
        }
    }


    private fun insertDatatoDatabase() {
        val nombre = binding.txtNombreCat.text.toString()
        val idRestaurante = currentRestaurante.id
        if (inputCheckEmpty(nombre)) {
            val categoria = Categoria(0, idRestaurante = idRestaurante, nombre = nombre)
            db.categoriaDao().insert(categoria)
            Snackbar.make(binding.root.rootView, "Categoria registrada", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            findNavController().popBackStack()
        } else {
            Snackbar.make(binding.root.rootView, "Rellenar los campos", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()        }
    }


    private fun inputCheckEmpty(
        nombre: String
    ): Boolean {
        return !(TextUtils.isEmpty(nombre))
    }


}