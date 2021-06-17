package com.example.navia_assign.core.service

import com.example.navia_assign.core.data.Diet
import retrofit2.http.GET

interface Service {
    @GET("/dummy/")
    suspend fun dietPlan() : Diet
}