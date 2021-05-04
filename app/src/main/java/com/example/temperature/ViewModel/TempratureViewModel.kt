package com.example.temperature.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.temperature.temp.repository.TempRepository
import com.example.temperature.temp.repository.model.Main
import com.example.temperature.temp.repository.model.Temprature

class TempratureViewModel() : ViewModel() {
    private var _viewModel: MutableLiveData<Main> = MutableLiveData()

    val temprature: LiveData<Main> get() = _viewModel

    fun getProducts(context: Context) {
        _viewModel = TempRepository.getTemprature(context)
    }
}