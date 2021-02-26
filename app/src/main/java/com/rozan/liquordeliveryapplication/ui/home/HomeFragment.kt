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
import com.rozan.liquordeliveryapplication.entity.Aila

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private var ailaList= arrayListOf<Aila>()
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
            val context=root.context
            ailaRecyclerView(root,context)
        })


        return root
    }

    private fun ailaRecyclerView(view: View,context: Context) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val adapter = AilaAdapter(ailaList,context)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = adapter
        loadAila()
    }
    private fun loadAila(){
        ailaList.add(Aila(ailaPrice = 1550.0,ailaMl = "750",ailaName = "Khukuri rum"))
        ailaList.add(Aila(ailaPrice = 1550.0,ailaMl = "750",ailaName = "Khukuri rum"))
        ailaList.add(Aila(ailaPrice = 1550.0,ailaMl = "750",ailaName = "Khukuri rum"))
    }


}