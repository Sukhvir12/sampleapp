package com.example.temperature.temp.repository.model

import com.example.temperature.database.Temperature

object TemperatureMapper {
    fun mapToTemparture(temprature: Temperature): Main {
        return Main(
            temp = temprature.temp,
            feelsLike = temprature.feelsLike,
            tempMin = temprature.tempMin,
            tempMax = temprature.tempMax,
            pressure = temprature.pressure,
            humidity = temprature.humidity
        )
    }

    fun mapfromTemparture(main: Main): Temperature {
        return Temperature(
            uid = 3,
            temp = main.temp,
            feelsLike = main.feelsLike,
            tempMin = main.tempMin,
            tempMax = main.tempMax,
            pressure = main.pressure,
            humidity = main.humidity
        )
    }
}