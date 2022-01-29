package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentMainBinding
import com.example.gestorrestaurantes.dal.models.Restaurante
import com.example.gestorrestaurantes.ui.adapters.RestauranteListAdapter


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RestauranteListAdapter
    private lateinit var listRestaurante: ArrayList<Restaurante>
    private lateinit var db:AppDatabase



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        db= AppDatabase.getInstance(requireContext())
        loadBaseData()
        setupEventListener()
        setupRecyclerView()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        listRestaurante = db.restauranteDao().getAll() as ArrayList<Restaurante>
        adapter.setData(listRestaurante)
    }
    private fun setupEventListener() {
        binding.btnAddRestaurante.setOnClickListener {
            findNavController().navigate(R.id.action_add_restaurante)
        }
    }
    private fun setupRecyclerView(){
        adapter = RestauranteListAdapter(listRestaurante)
        val linearLayoutV = LinearLayoutManager(requireContext())
        binding.lstRestaurantes.adapter = adapter
        binding.lstRestaurantes.layoutManager = linearLayoutV
    }
    private  fun loadBaseData(){
        listRestaurante = db.restauranteDao().getAll() as ArrayList<Restaurante>
    }



}