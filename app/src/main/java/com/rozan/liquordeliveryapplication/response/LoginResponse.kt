package com.rozan.liquordeliveryapplication.response

data class LoginResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val token:String?=null,
    val userId:String?=null
        ){

}