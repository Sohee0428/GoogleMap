package com.example.aoppart4chapter03.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationLatLngEntity(
    val latitude: String,
    val longitude: String
) : Parcelable