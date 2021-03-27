package com.rozan.liquordeliveryapplication.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.adapter.FavoriteAdapter
import com.rozan.liquordeliveryapplication.db.AilaDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(requireContext())

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
                ViewModelProvider(this).get(FavoritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesViewModel.text.observe(viewLifecycleOwner, Observer {
            val context = root.context
            favRecyclerView(root, context)
        })
        return root
    }

    private fun favRecyclerView(view: View, context: Context) {
        favRecyclerView=view.findViewById(R.id.favRV)

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val fav= AilaDB.getInstance(context).getFavoritesDAO().loadAllFavorite()
                withContext(Main){
                    favRecyclerView.adapter=FavoriteAdapter(fav,context)
                    favRecyclerView.layoutManager= LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }

            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}