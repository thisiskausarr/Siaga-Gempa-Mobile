package com.example.siaga.config

import com.example.siaga.data.USGSEarthQuakeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://earthquake.usgs.gov"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usgsEarthQuakeService: USGSEarthQuakeService by lazy {
        retrofit.create(USGSEarthQuakeService::class.java)
    }
}
