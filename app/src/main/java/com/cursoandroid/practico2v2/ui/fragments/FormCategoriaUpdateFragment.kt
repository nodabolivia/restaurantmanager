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
import com.cursoandroid.practico2v2.databinding.FragmentFormCategoriaUpdateBinding
import com.example.gestorrestaurantes.dal.models.Categoria


class FormCategoriaUpdateFragment : Fragment() {
    private val args by navArgs<FormCategoriaUpdateFragmentArgs>()
    private lateinit var db: AppDatabase
    private lateinit var currentCategoria: Categoria

    private var _binding: FragmentFormCategoriaUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormCategoriaUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentCategoria = it.currentCategoria
        }
        loadDataCategoria()
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnActualizarCategoria.setOnClickListener {
            updateDatatoDatabase()
        }

    }

    private fun loadDataCategoria(){
//        currentCategoria = args.currentCategoria
        binding.txtNombreCatUpdate.setText(currentCategoria.nombre.toString())


    }
    private fun updateDatatoDatabase(){
//        currentCategoria = args.currentCategoria
        val id = currentCategoria.id
        val idRestaurante = currentCategoria.idRestaurante
        val nombre = binding.txtNombreCatUpdate.editableText.toString()

        if (inputCheckEmpty( nombre)) {
            val categoria = Categoria(id,idRestaurante=idRestaurante,nombre=nombre)
            db.categoriaDao().update(categoria)
            Toast.makeText(requireContext(),"Categoria actualizada", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        } else {
            Toast.makeText(requireContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheckEmpty(

        nombre: String
    ): Boolean {
        return !(TextUtils.isEmpty(nombre) )
    }
}