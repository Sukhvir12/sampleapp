package com.example.temperature.temp.repository

import androidx.lifecycle.MutableLiveData
import com.example.temperature.temp.repository.model.ProductItem

interface IProductRepository {
    fun getProducts(): MutableLiveData<List<ProductItem>>
}