package com.example.temperature.database

object RoomDatabaseService {

    fun getRecords(db: AppDatabase): List<Temperature> {
        return db.userDao().getAll()
    }

    fun insert(db: AppDatabase, temperature: Temperature) {
        db.userDao().insertAll(temperature)
    }
}