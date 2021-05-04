package com.example.temperature.temp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.temperature.temp.repository.model.Main
import com.example.temperature.temp.repository.model.Temprature

interface ITempRepository {
    fun getTemprature(context: Context): MutableLiveData<Main>
}