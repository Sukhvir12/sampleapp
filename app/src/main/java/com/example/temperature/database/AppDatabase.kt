package com.example.temperature.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Temperature::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): TemparatureDTO

    companion object {
        lateinit var INSTANCE: AppDatabase

        fun getAppDataBase(context: Context): AppDatabase {

            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "myDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }

        /*fun destroyDataBase(){
            INSTANCE = null
        }
*/
    }
}