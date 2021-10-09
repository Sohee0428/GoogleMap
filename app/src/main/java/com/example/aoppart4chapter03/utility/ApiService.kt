package com.example.aoppart4chapter03.utility

import com.example.aoppart4chapter03.Key
import com.example.aoppart4chapter03.Url
import com.example.aoppart4chapter03.Url.GET_TAMP_REVERSE_GEO_CODE
import com.example.aoppart4chapter03.Url.GET_TMAP_LOCATION
import com.example.aoppart4chapter03.response.address.AddressInfoResponse
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

    @GET(GET_TAMP_REVERSE_GEO_CODE)
    suspend fun getReverseGeoCode(
        @Header("appKey") appKey: String = Key.TMAP_API,
        @Query("version") version: Int = 1,
        @Query("callback") callback: String? = null,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("coordType") coordType: String? = null,
        @Query("addressType") addressType: String? = null
    ): Response<AddressInfoResponse>
}