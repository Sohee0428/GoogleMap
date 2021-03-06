package com.example.aoppart4chapter03.response.address

data class AddressInfo(
    val addressKey: String,
    val addressType: String,
    val adminDong: String,
    val adminDongCode: String,
    val adminDongCoord: AdminDongCoord,
    val buildingIndex: String,
    val buildingName: String,
    val bunji: String,
    val city_do: String,
    val eup_myun: String,
    val fullAddress: String,
    val gu_gun: String,
    val legalDong: String,
    val legalDongCode: String,
    val legalDongCoord: LegalDongCoord,
    val mappingDistance: String,
    val ri: String,
    val roadAddressKey: String,
    val roadCode: String,
    val roadCoord: RoadCoord,
    val roadName: String
)