package com.cursoandroid.practico2v2.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentDetalleRestauranteBinding
import com.example.gestorrestaurantes.dal.models.Categoria
import com.example.gestorrestaurantes.dal.models.Restaurante
import com.example.gestorrestaurantes.ui.adapters.CategoriaListAdapter
import com.google.android.material.snackbar.Snackbar


class DetalleRestauranteFragment : Fragment() {
    private val args by navArgs<DetalleRestauranteFragmentArgs>()
    private lateinit var currentRestaurante: Restaurante
    private lateinit var listCategorias: ArrayList<Categoria>

    private lateinit var adapter: CategoriaListAdapter
    private var _binding: FragmentDetalleRestauranteBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleRestauranteBinding.inflate(inflater, container, false)
        val view = binding.root

        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentRestaurante = it.currentRestaurante
        }
        listCategorias = db.categoriaDao()
            .getAllByRestaurante(currentRestaurante.id) as ArrayList<Categoria>
        loadDataRestaurante()
        setupEventListener()
        setupRecyclerView()
        setHasOptionsMenu(true)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
//        setDataRestaurante(db.restauranteDao().getById(args.currentRestaurante!!.id))
        setDataRestaurante(db.restauranteDao().getById(currentRestaurante.id))
        loadDataRestaurante()
//        listCategorias = db.categoriaDao()
//            .getAllByRestaurante(args.currentRestaurante!!.id) as ArrayList<Categoria>
        listCategorias = db.categoriaDao()
            .getAllByRestaurante(currentRestaurante.id) as ArrayList<Categoria>
        adapter.setData(listCategorias)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupEventListener() {
        binding.btnEditRestaurante.setOnClickListener {
            setupActionEditRestaurante()
        }
        binding.btnAddCategoria.setOnClickListener {
            setupActionAddCategoria()
        }
    }

    private fun setupRecyclerView() {
        adapter = CategoriaListAdapter(listCategorias)
        val linearLayoutV = LinearLayoutManager(requireContext())
        binding.lstCategorias.adapter = adapter
        binding.lstCategorias.layoutManager = linearLayoutV
    }

    private fun setupActionEditRestaurante() {
        val action = DetalleRestauranteFragmentDirections.actionEditRestaurante(currentRestaurante)
        findNavController().navigate(action)
    }

    private fun setupActionAddCategoria() {
        val action = DetalleRestauranteFragmentDirections.actionAddCategoria(currentRestaurante)
        findNavController().navigate(action)
    }

    fun setDataRestaurante(currentRestaurante: Restaurante) {
        this.currentRestaurante = currentRestaurante
    }

    private fun loadDataRestaurante() {
        //currentContacto = args.currentContactoWithTelefono!!.contacto
        binding.resNombre.text = "${currentRestaurante.nombre}"
        binding.resTelefono.text = "${currentRestaurante.telefono}"
    }

    private fun deleteItem() {
        db.restauranteDao().delete(currentRestaurante)
        Snackbar.make(requireView(), "Restaurante eliminado", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        findNavController().popBackStack()
    }

    private fun deleteItemAdvance() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si") { _, _ ->
            db.restauranteDao().delete(currentRestaurante)

            Toast.makeText(
                requireContext(),
                "Restaurante exitosamente eliminado: ${currentRestaurante.nombre}",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("¿Deseas eliminar ${currentRestaurante.nombre}?")
        builder.setMessage("¿Estas seguro que deseas eliminar ${currentRestaurante.nombre}?")
        builder.create().show()
    }


}