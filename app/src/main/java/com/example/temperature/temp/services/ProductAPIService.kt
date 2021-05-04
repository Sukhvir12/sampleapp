package com.example.temperature.temp.services

import com.example.temperature.temp.repository.model.Product
import retrofit2.http.GET

interface ProductAPIService {
    @GET("test/test.php")
    suspend fun getProducts(): Product
}