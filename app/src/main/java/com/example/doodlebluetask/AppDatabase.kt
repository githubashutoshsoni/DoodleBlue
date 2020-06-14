package com.example.doodlebluetask


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Store::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): FoodDao

}
