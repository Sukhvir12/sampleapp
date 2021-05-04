package com.example.temperature.temp.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRetroFitBuilder {

    //    interceptor.
    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun makeService(): ProductAPIService {
        return Retrofit.Builder()
            .baseUrl("https://domywork.ca/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProductAPIService::class.java)
    }
}