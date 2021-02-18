package com.rozan.liquordeliveryapplication.api

import com.rozan.liquordeliveryapplication.entity.User
import com.rozan.liquordeliveryapplication.entity.Users
import com.rozan.liquordeliveryapplication.response.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    //Registering User
    @POST("register")
    suspend fun registerUser(
        @Body user: Users
    ):Response<RegistrationResponse>

}