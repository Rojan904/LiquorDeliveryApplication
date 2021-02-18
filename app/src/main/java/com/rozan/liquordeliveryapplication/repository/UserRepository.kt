package com.rozan.liquordeliveryapplication.repository

import com.rozan.liquordeliveryapplication.api.APIRequest
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.api.UserAPI
import com.rozan.liquordeliveryapplication.entity.User
import com.rozan.liquordeliveryapplication.entity.Users
import com.rozan.liquordeliveryapplication.response.LoginResponse
import com.rozan.liquordeliveryapplication.response.RegistrationResponse

class UserRepository:APIRequest() {
    val api=ServiceBuilder.buildService(UserAPI::class.java)
    suspend fun registerUser(user: Users):RegistrationResponse{
    return apiRequest {
        api.registerUser(user)
    }
}
    suspend fun checkUser(username: String, password: String): LoginResponse {
        return apiRequest {
            api.checkUser(username, password)
        }
    }
}