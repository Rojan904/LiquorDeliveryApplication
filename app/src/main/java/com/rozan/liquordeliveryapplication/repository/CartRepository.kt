package com.rozan.liquordeliveryapplication.repository

import android.content.Context
import com.rozan.liquordeliveryapplication.api.APIRequest
import com.rozan.liquordeliveryapplication.api.CartAPI
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.entity.Cart
import com.rozan.liquordeliveryapplication.response.CartResponse

class CartRepository:APIRequest() {
    val cartAPI= ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addToCart(cart:Cart):CartResponse{
        return apiRequest {
            cartAPI.addToCart(ServiceBuilder.token!!,cart)
        }
    }

    suspend fun getCart():CartResponse{
        return apiRequest {
            cartAPI.getCart(ServiceBuilder.token!!)
        }
    }
}