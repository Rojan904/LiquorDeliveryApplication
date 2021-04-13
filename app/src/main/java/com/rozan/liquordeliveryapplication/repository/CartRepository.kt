package com.rozan.liquordeliveryapplication.repository

import android.content.Context
import com.rozan.liquordeliveryapplication.api.APIRequest
import com.rozan.liquordeliveryapplication.api.CartAPI
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.db.AilaDB
import com.rozan.liquordeliveryapplication.entity.Aila
import com.rozan.liquordeliveryapplication.entity.Cart
import com.rozan.liquordeliveryapplication.response.CartResponse

class CartRepository:APIRequest() {
    val cartAPI= ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addToCart(id:String,cart:Cart):CartResponse{
        return apiRequest {
            cartAPI.addToCart(ServiceBuilder.token!!,id,cart)
        }
    }

    suspend fun getCart(context: Context):MutableList<Cart>{
        val response= apiRequest {
            cartAPI.getCart(ServiceBuilder.token!!)
        }

        val lstCart:MutableList<Cart> = response.data!!
        AilaDB.getInstance(context).clearAllTables()
        AilaDB.getInstance(context).getCartDAO().insertCart(lstCart)

        val allCart:MutableList<Cart> = AilaDB.getInstance(context).getCartDAO().getAllCart()
        return allCart

    }
}