package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.databinding.FragmentDetallePlatoBinding
import com.example.gestorrestaurantes.dal.models.Plato
import com.google.android.material.snackbar.Snackbar

class DetallePlatoFragment : Fragment() {
    private val args by navArgs<DetallePlatoFragmentArgs>()
    private lateinit var currentPlato:Plato
    private var color:String =""
    private var _binding: FragmentDetallePlatoBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallePlatoBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentPlato = it.currentPlato
        }

        loadDataPlato()
        setHasOptionsMenu(true)
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_plato_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_plato_delete) {
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        super.onResume()
        setDataPlato(db.platoDao().getById(currentPlato.id))
        loadDataPlato()

    }
    private fun setupEventListener() {
        binding.btnEditPlato.setOnClickListener {
            setupActionEditPlato()
        }
//        binding.btncircle.setOnClickListener {
//            Snackbar.make(requireView(), "cicle", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//        binding.colorOne.setOnClickListener {
//            binding.layoutDetallePlato.setBackgroundResource(R.color.teal_200)
//            color = "Rosa"
//            Snackbar.make(requireView(), color, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//        binding.colorTwo.setOnClickListener {
//            binding.layoutDetallePlato.setBackgroundResource(R.color.white)
//            color = "lila"
//            Snackbar.make(requireView(), color, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

    }
    private fun setupActionEditPlato() {
        val action =
            DetallePlatoFragmentDirections.actionEditPlato(currentPlato)
        findNavController().navigate(action)
    }
    private fun loadDataPlato() {
        binding.plaNombre.text = "${currentPlato.nombre}"
        binding.plaDescripcion.text = "${currentPlato.descripcion}"
    }
    fun setDataPlato(currentPlato: Plato) {
        this.currentPlato = currentPlato
    }
    private fun deleteItem() {
        db.platoDao().delete(currentPlato)
        Snackbar.make(requireView(), "Plato eliminada", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        findNavController().popBackStack()
    }

}