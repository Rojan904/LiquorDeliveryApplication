package com.rozan.liquordeliveryapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.adapter.AilaAdapter
import com.rozan.liquordeliveryapplication.adapter.AilaCategoryAdapter
import com.rozan.liquordeliveryapplication.db.AilaDB
import com.rozan.liquordeliveryapplication.entity.Aila
import com.rozan.liquordeliveryapplication.model.AilaCategory
import com.rozan.liquordeliveryapplication.repository.AilaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var categRecyclerView: RecyclerView

    private var ailaList = arrayListOf<Aila>()
    private var ailaCategory = arrayListOf<AilaCategory>()
    private lateinit var homeViewModel: HomeViewModel
    override fun onAttach(context: Context) {
        super.onAttach(requireContext())

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            val context = root.context
            categoryRecyclerView(root, context)
            ailaRecyclerView(root, context)
        })
        return root
    }

    private fun categoryRecyclerView(view: View, context: Context) {
        categRecyclerView = view.findViewById(R.id.categRecyclerView)
        val adapter = AilaCategoryAdapter(ailaCategory, context)
        categRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        categRecyclerView.adapter = adapter

        loadCateg()
    }

    private fun loadCateg() {
        ailaCategory.add((AilaCategory(categName = "Whisky", categImage = "https://i.pinimg.com/originals/63/68/3c/63683c9df2d0c015fa3320297f216780.jpg")))
        ailaCategory.add((AilaCategory(categName = "Rum", categImage = "https://cdn5.vectorstock.com/i/1000x1000/37/84/cartoon-bottle-rum-vector-29563784.jpg")))
        ailaCategory.add((AilaCategory(categName = "Vodka", categImage = "https://st3.depositphotos.com/3557671/13489/v/950/depositphotos_134893664-stock-illustration-glass-bottle-of-vodka-icon.jpg")))
        ailaCategory.add((AilaCategory(categName = "Wine", categImage = "http://dentistinsaginawtx.com/wp-content/uploads/2014/07/red-wine.jpg")))
        ailaCategory.add((AilaCategory(categName = "Beer", categImage = "https://i.pinimg.com/736x/d7/5a/3c/d75a3c612cf968ce7a81e011ac576cdb.jpg")))
    }

    private fun ailaRecyclerView(view: View, context: Context) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView2 = view.findViewById(R.id.recyclerView2)
        val adapter = AilaAdapter(ailaList, context)

        recyclerView2.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView2.adapter = adapter
        loadAila(context)
    }

    private fun loadAila(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val ailaRepository = AilaRepository()
                val response = ailaRepository.getAllAila(context)

                withContext(Dispatchers.Main) {
                    recyclerView.adapter = AilaAdapter(response, context)
                    recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}