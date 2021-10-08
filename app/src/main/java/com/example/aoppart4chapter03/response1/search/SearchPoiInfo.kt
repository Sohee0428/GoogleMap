package com.example.aoppart4chapter03.response1.search

data class SearchPoiInfo(
    val count: String,
    val page: String,
    val pois: Pois,
    val totalCount: String
)