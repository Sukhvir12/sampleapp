package com.example.temperature.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.temperature.temp.repository.ProductRespository
import com.example.temperature.temp.repository.model.ProductItem

class ProductViewModel() :
    ViewModel() {
    private var _ProductMutuableLiveData = MutableLiveData<List<ProductItem>>()

    val productLiveData get() = _ProductMutuableLiveData

    fun getProducts() {
        _ProductMutuableLiveData = ProductRespository.getProducts()
    }

    fun deleteProduct(id: String) {
        val temp = _ProductMutuableLiveData.value as List<ProductItem>
        temp.indexOfFirst { it.productId == id }?.let { searchIndex ->
            _ProductMutuableLiveData.value = temp.drop(searchIndex)
        }
    }

}