package com.kst.creditscoreapp.network

import com.kst.creditscoreapp.network.model.Credit
import retrofit2.http.GET

interface CreditService {
    @GET("/endpoint.json")
    suspend fun getDetail(): Credit
}