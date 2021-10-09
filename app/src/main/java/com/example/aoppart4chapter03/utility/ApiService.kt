package com.example.aoppart4chapter03.utility

import com.example.aoppart4chapter03.Key
import com.example.aoppart4chapter03.Url.GET_TMAP_LOCATION
import com.example.aoppart4chapter03.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET(GET_TMAP_LOCATION)
    suspend fun getSearchLocation(
        @Header("appKey") appKey: String = Key.TMAP_API,
        @Query("version") version: Int = 1,
        @Query("count") count: Int = 20,
        @Query("searchKeyword") keyword: String
    ): Response<SearchResponse>
}