package com.rozan.liquordeliveryapplication.api

import com.rozan.liquordeliveryapplication.response.GetAllAilaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AilaAPI {

    @GET("aila/all")
    suspend fun getAllAila(
            @Header("Authorization") token:String
    ):Response<GetAllAilaResponse>
}