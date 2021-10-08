package com.example.aoppart4chapter03.response1.search

data class EvCharger(
    val chargerId: String,
    val chargingDateTime: String,
    val isAvailable: String,
    val isFast: String,
    val operatorId: String,
    val operatorName: String,
    val powerType: String,
    val stationId: String,
    val status: String,
    val type: String,
    val updateDateTime: String
)