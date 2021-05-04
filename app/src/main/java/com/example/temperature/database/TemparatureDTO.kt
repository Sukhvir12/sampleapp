package com.example.temperature.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TemparatureDTO {
    @Query("SELECT * FROM temperature")
    fun getAll(): List<Temperature>

   /* @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>*/

    /*@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Insert
    fun insertAll(vararg temperature: Temperature)

    @Delete
    fun delete(temperature: Temperature)
}