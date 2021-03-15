package com.rozan.liquordeliveryapplication.api

import com.rozan.liquordeliveryapplication.entity.Cart
import com.rozan.liquordeliveryapplication.response.CartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CartAPI {

    @POST("add/cart")
    suspend fun addToCart(
            @Header("Authorization") token:String,
            @Body cart: Cart
    ):Response<CartResponse>
}