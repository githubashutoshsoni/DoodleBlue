package com.example.doodlebluetask

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel constructor(context: Application) : AndroidViewModel(context) {

    lateinit var database: AppDatabase


//    var getAllList: LiveData<List<Store>> = database.cartDao().getAll()

    init {
        val result = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "Tasks.db"
        ).build()
        database = result
    }

    fun insertTasks(list: ArrayList<Store>) {

        viewModelScope.launch {
            withContext()

            database.cartDao().insert(list)

        }
    }


}