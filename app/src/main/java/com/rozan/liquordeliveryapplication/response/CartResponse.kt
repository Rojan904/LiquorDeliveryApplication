package com.rozan.liquordeliveryapplication.response

import com.rozan.liquordeliveryapplication.entity.Cart

data class CartResponse(
  val success:Boolean?=null,
  val message:String?=null,
  val data:MutableList<Cart>?=null
)