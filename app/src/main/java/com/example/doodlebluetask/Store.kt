package com.example.doodlebluetask

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(primaryKeys = ["foodName"])
@Parcelize
data class Store(
    var foodName: String = "",
    var foodDescription: String = "",
    var count: Int = 0
) : Parcelable