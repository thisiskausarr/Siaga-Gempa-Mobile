package com.example.siaga.repository

import android.icu.util.Calendar
import android.util.Log
import com.example.siaga.config.RetrofitInstance
import com.example.siaga.model.UsgsEarthQuake
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat


class EarthQuakeRepository {
    private val earthQuakesService = RetrofitInstance.usgsEarthQuakeService

    suspend fun getEarthQuakes(startTime: String, endTime: String
                               ,onResult: (UsgsEarthQuake) -> Unit)= withContext(Dispatchers.IO){

        val response = RetrofitInstance.usgsEarthQuakeService.getEarthQuakes( starttime=startTime, endtime = endTime)

        onResult.invoke(response.body()!!)
    }
}