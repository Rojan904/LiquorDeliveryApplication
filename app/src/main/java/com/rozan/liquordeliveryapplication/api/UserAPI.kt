package com.rozan.liquordeliveryapplication.api

import com.rozan.liquordeliveryapplication.entity.User
import com.rozan.liquordeliveryapplication.entity.Users
import com.rozan.liquordeliveryapplication.response.LoginResponse
import com.rozan.liquordeliveryapplication.response.RegistrationResponse
import com.rozan.liquordeliveryapplication.response.UpdateUserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    //Registering User
    @POST("register")
    suspend fun registerUser(
        @Body user: Users
    ):Response<RegistrationResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("username") username:String,
        @Field("password") password:String
    ):Response<LoginResponse>

    @PUT("user/update/{id}")
    suspend fun updateUser(
            @Header("Authorization") token: String,
            @Path("id") id: String,
            @Body user: Users
    ) : Response<UpdateUserResponse>
}