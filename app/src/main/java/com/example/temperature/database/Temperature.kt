package com.example.temperature.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temperature")
data class Temperature(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo(name = "feelsLike") val feelsLike: Double,
    @ColumnInfo(name = "tempMin") val tempMin: Double,
    @ColumnInfo(name = "tempMax") val tempMax: Long,
    @ColumnInfo(name = "pressure") val pressure: Long,
    @ColumnInfo(name = "humidity") val humidity: Long
)