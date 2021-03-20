package com.rozan.liquordeliveryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rozan.liquordeliveryapplication.adapter.CartAdapter
import com.rozan.liquordeliveryapplication.entity.Cart
import com.rozan.liquordeliveryapplication.repository.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CartActivity : AppCompatActivity() {
    private lateinit var cartRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRecyclerView()
    }

    private fun cartRecyclerView() {
        cartRecyclerView=findViewById(R.id.cartRecyclerView)


        loadCart()
    }

    private fun loadCart() {

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val cartRepository=CartRepository()
                val response=cartRepository.getCart(this@CartActivity)

                withContext(Main){
                    cartRecyclerView.adapter=CartAdapter(response,this@CartActivity)
                    cartRecyclerView.layoutManager=LinearLayoutManager(this@CartActivity)
                }
            }
            catch (ex:Exception){
                withContext(Main){
                    Toast.makeText(this@CartActivity,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}