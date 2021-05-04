package com.example.temperature.temp.services

import com.example.temperature.temp.repository.model.Temprature
import retrofit2.http.GET

interface TempratureAPIService {
    @GET("data/2.5/weather?q=Toronto&units=imperial&appid=7a6a1f819097fd4faa4aa8f78d994ff3")
    suspend fun getTemparature(): Temprature
}