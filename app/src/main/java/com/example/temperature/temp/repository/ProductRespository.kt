package com.example.temperature.temp.repository

import androidx.lifecycle.MutableLiveData
import com.example.temperature.temp.repository.model.ProductItem
import com.example.temperature.temp.services.ProductRetroFitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ProductRespository : IProductRepository {

    var job: Job? = null
    override fun getProducts(): MutableLiveData<List<ProductItem>> {
        job = Job()
        return object : MutableLiveData<List<ProductItem>>() {
            override fun onActive() {
                super.onActive()
                job?.let { thisJob ->
                    CoroutineScope(IO + thisJob).launch {
                        val productList = ProductRetroFitBuilder.makeService().getProducts().product
                        withContext(Main) {
                            value = productList
                        }
                    }

                }
            }
        }
    }
}