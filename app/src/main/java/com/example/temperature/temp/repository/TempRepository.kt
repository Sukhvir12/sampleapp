package com.example.temperature.temp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.temperature.Utility
import com.example.temperature.database.AppDatabase
import com.example.temperature.database.RoomDatabaseService
import com.example.temperature.database.Temperature
import com.example.temperature.temp.repository.model.Main
import com.example.temperature.temp.repository.model.TemperatureMapper.mapToTemparture
import com.example.temperature.temp.repository.model.TemperatureMapper.mapfromTemparture
import com.example.temperature.temp.services.RetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.lang.Exception

object TempRepository : ITempRepository {

    private var job: Job? = null

    override fun getTemprature(context: Context): MutableLiveData<Main> {
        job = Job()
        return object : MutableLiveData<Main>() {
            override fun onActive() {
                super.onActive()
                job?.let { thisJob ->
                    CoroutineScope(IO + thisJob).launch {
                        val temperatureDb = getDBTemprature(context)
                        temperatureDb?.let {
                            Utility.log("database", "db get record")
                            mapToTemparture(temperatureDb)
                        } ?: run {
                            val temprature = RetrofitBuilder.makeService().getTemparature()

                            /*CoroutineScope(IO).launch {
                                try {

                                    RoomDatabaseService.insert(
                                        AppDatabase.getAppDataBase(context),
                                        mapfromTemparture(temprature.main)
                                    )
                                } catch (e: Exception) {
                                    Utility.log("database", "exception = ${e.message}")
                                }
                                Utility.log("database", "db insert record")
                            }*/

                            withContext(Main) {
                                Utility.log("database", "API get record")
                                value = temprature.main
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getDBTemprature(context: Context): Temperature? {
        val job = Job()
        var temp: Temperature? = null
        CoroutineScope(IO + job).launch {
            val jobA =
                async { RoomDatabaseService.getRecords(AppDatabase.getAppDataBase(context)) }
            Utility.log("database", "getDBTemprature")
            temp = jobA.await().firstOrNull()
            Utility.log("database", "getDBTemprature temp ${temp?.temp}")

        }
        Utility.log("database", "getDBTemprature returned")
        return temp
    }
}