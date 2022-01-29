package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentDetalleCategoriaBinding
import com.example.gestorrestaurantes.dal.models.Categoria
import com.example.gestorrestaurantes.dal.models.Plato
import com.example.gestorrestaurantes.ui.adapters.PlatoListAdapter
import com.google.android.material.snackbar.Snackbar


class DetalleCategoriaFragment : Fragment() {
    private val args by navArgs<DetalleCategoriaFragmentArgs>()
    private lateinit var currenCategoria: Categoria
    private lateinit var listPlatos: ArrayList<Plato>
    private lateinit var adapter:PlatoListAdapter
    private var _binding: FragmentDetalleCategoriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleCategoriaBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currenCategoria = it.currentCategoria!!
            //listPlatos = it.currentCategoriaWithPlatos!!.platos as ArrayList<Plato>
        }
        listPlatos = db.platoDao()
            .getAllByCategoria(currenCategoria.id) as ArrayList<Plato>
        loadDataCategoria()
        setupEventListener()
        setHasOptionsMenu(true)
        setupRecyclerView()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setDataCategoria(db.categoriaDao().getById(currenCategoria.id))
        loadDataCategoria()
        listPlatos = db.platoDao()
            .getAllByCategoria(currenCategoria.id) as ArrayList<Plato>
        adapter.setData(listPlatos)
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
        binding.btnEditCategoria.setOnClickListener {
            setupActionEditCategoria()
        }
        binding.btnAddPlato.setOnClickListener {
            setupActionAddPlato()
        }
    }
    private fun setupActionEditCategoria() {
        val action =
            DetalleCategoriaFragmentDirections.actionEditCategoria(currentCategoria = currenCategoria)
        findNavController().navigate(action)
    }

    private fun setupActionAddPlato() {
        val action =
            DetalleCategoriaFragmentDirections.actionAddPlato(currentCategoria = currenCategoria)
        findNavController().navigate(action)
    }
    private fun setupRecyclerView(){
        adapter = PlatoListAdapter(listPlatos)

        val linearLayoutV = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.lstPlatos.adapter = adapter
        binding.lstPlatos.layoutManager = linearLayoutV
    }

    private fun loadDataCategoria() {
        binding.catNombre.text = "Categoria - ${currenCategoria.nombre}"
    }
    fun setDataCategoria(currenCategoria: Categoria) {
        this.currenCategoria = currenCategoria
    }
    private fun deleteItem() {
        db.categoriaDao().delete(currenCategoria)
        Snackbar.make(requireView(), "Categoria eliminada", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        findNavController().popBackStack()
    }


}

