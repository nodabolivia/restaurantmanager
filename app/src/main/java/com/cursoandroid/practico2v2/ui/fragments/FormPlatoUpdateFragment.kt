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
import com.cursoandroid.practico2v2.databinding.FragmentFormPlatoUpdateBinding
import com.example.gestorrestaurantes.dal.models.Plato


class FormPlatoUpdateFragment : Fragment() {
    private val args by navArgs<FormPlatoUpdateFragmentArgs>()
    private var _binding: FragmentFormPlatoUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentPlato: Plato
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormPlatoUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentPlato = it.currentPlato
        }
        loadDataPlato()
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnActualizarPlato.setOnClickListener {
            updateDatatoDatabase()
        }

    }
    private fun loadDataPlato(){
//        currentPlato = args.currentPlato
        binding.txtNombrePlaUpdate.setText(currentPlato.nombre)
        binding.txtDescripcionPlaUpdate.setText(currentPlato.descripcion)


    }
    private fun updateDatatoDatabase(){
//       currentPlato = args.currentPlato
        val id = currentPlato.id
        val idRestaurante = currentPlato.idRestaurante
        val idCategoria = currentPlato.idCategoria
        val nombre = binding.txtNombrePlaUpdate.editableText.toString()
        val descripcion = binding.txtDescripcionPlaUpdate.editableText.toString()

        if (inputCheckEmpty( nombre,descripcion)) {
            val plato = Plato(id,idRestaurante=idRestaurante,idCategoria=idCategoria,nombre=nombre,descripcion=descripcion,currentPlato.urlImg)
            db.platoDao().update(plato)
            Toast.makeText(requireContext(),"Plato actualizada", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        } else {
            Toast.makeText(requireContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheckEmpty(

        nombre: String
        ,descripcion: String
    ): Boolean {
        return !(TextUtils.isEmpty(nombre) && TextUtils.isEmpty(descripcion) )
    }
}