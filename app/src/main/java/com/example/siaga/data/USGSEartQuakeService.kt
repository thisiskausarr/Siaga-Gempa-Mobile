package com.example.siaga.data

import com.example.siaga.model.UsgsEarthQuake
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface USGSEarthQuakeService {
    @GET("/fdsnws/event/1/query")
    suspend fun getEarthQuakes(
        @Query("format") format: String= "geojson",
        @Query("starttime") starttime: String,
        @Query("endtime") endtime: String,
        @Query("minlatitude") minlatitude: String ="-10.6",
        @Query("minlongitude") minlongitude: String="95",
        @Query("maxlatitude") maxlatitude: String="6.1",
        @Query("maxlongitude") maxlongitude: String="141",
    ): Response<UsgsEarthQuake>
}
