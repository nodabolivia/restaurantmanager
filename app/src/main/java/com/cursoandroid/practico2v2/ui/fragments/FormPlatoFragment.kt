package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentFormPlatoBinding
import com.cursoandroid.practico2v2.ui.controllers.ImageController
import com.example.gestorrestaurantes.dal.models.Categoria
import com.example.gestorrestaurantes.dal.models.Plato
import com.google.android.material.snackbar.Snackbar

class FormPlatoFragment : Fragment() {
    private val args by navArgs<FormPlatoFragmentArgs>()
    private lateinit var db: AppDatabase
    private lateinit var currentCategoria: Categoria
    private var _binding: FragmentFormPlatoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormPlatoBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentCategoria = it.currentCategoria
        }
        setupEventListener()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnAddPlato.setOnClickListener {
            saveData()
        }
        binding.imgSelectPla.setOnClickListener{
            ImageController.selectPhotoFromGallery(requireActivity(),1)
        }
    }

    private fun saveData() {
        val nombre = binding.txtNombrePla.text.toString()
        val descripcion = binding.txtDescripcionPla.text.toString()
        val idCategoria = currentCategoria.id
        val idRestaurante = currentCategoria.idRestaurante
        val rnds = (0..9999).random()
        if (inputCheckEmpty(nombre, descripcion)) {
            insertDatatoDatabase(
                nombre,
                descripcion,
                idCategoria,
                idRestaurante,
                R.drawable.ic_launcher_background + rnds
            )
        } else {
            Snackbar.make(binding.root.rootView, "Rellenar los campos", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun insertDatatoDatabase(
        nombre: String, descripcion: String, idCategoria: Int, idRestaurante: Int, img: Int
    ) {
        val plato = Plato(
            0,
            idRestaurante = idRestaurante,
            idCategoria = idCategoria,
            nombre = nombre,
            descripcion = descripcion,
            urlImg = img.toLong()
        )
        db.platoDao().insert(plato)

        Snackbar.make(binding.root.rootView, "Plato registrado", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        findNavController().popBackStack()
    }

    private fun inputCheckEmpty(
        nombre: String,
        descripcion: String
    ): Boolean {
        return !(TextUtils.isEmpty(nombre) && TextUtils.isEmpty(descripcion))
    }


}