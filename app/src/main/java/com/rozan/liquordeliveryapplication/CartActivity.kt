package com.rozan.liquordeliveryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rozan.liquordeliveryapplication.adapter.CartAdapter
import com.rozan.liquordeliveryapplication.entity.Cart

class CartActivity : AppCompatActivity() {
    private lateinit var cartRecyclerView: RecyclerView
    private var lstCart= arrayListOf<Cart>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRecyclerView()
    }

    private fun cartRecyclerView() {
        cartRecyclerView=findViewById(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager=LinearLayoutManager(this)
        cartRecyclerView.adapter=CartAdapter(lstCart,this)

        loadCart()
    }

    private fun loadCart() {
        lstCart.add(Cart(ailaImage = "https://i.pinimg.com/736x/d7/5a/3c/d75a3c612cf968ce7a81e011ac576cdb.jpg",
                ailaName = "Old Durbar",
        ailaPrice = 575.0,ailaMl = "180"
        ))
    }
}