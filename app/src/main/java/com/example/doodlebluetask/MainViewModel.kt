package com.example.doodlebluetask

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class MainViewModel constructor(application: Application) : AndroidViewModel(application) {


    var database: AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "DB")
        .allowMainThreadQueries().build()


    fun updateOrder(foodName: String, count: Int) {
        val value = database.cartDao().update(foodName, count)
        Log.d("ViewModel", "updateOrder: $value")

    }

    fun insertList(list: List<Store>) {


        database.cartDao().insert(list)


    }

    var list: LiveData<List<Store>> = database.cartDao().getAll()

}