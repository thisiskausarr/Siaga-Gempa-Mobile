package com.example.siaga.viewmodel

import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siaga.model.History
import com.example.siaga.repository.EarthQuakeRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class   HistoryViewModel(
    private val repository: EarthQuakeRepository = EarthQuakeRepository()
) : ViewModel() {
    var locationHistory by mutableStateOf(LocationHistory())
    var history by mutableStateOf(Histories())
    fun LoadLocation()= viewModelScope.launch {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val endtime = dateFormat.format(Calendar.getInstance().time)
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.MONTH, -1)

        val startTime = dateFormat.format(calendar.time)
        repository.getEarthQuakes(startTime = startTime, endTime = endtime){ // [long,lat]
            locationHistory =
                it.features?.map { f-> LatLng(f?.geometry?.coordinates?.get(1) ?: 0.0, f?.geometry?.coordinates?.get(0) ?: 0.0) }
                    ?.let { it1 -> locationHistory.copy(location = it1) }!!
        }
    }
    fun LoadHistory()= viewModelScope.launch {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateHistory = SimpleDateFormat("dd MMMM yyyy")
        val timeHistory = SimpleDateFormat("hh:mm a")
        val endtime = dateFormat.format(Calendar.getInstance().time)
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.MONTH, -1)

        val startTime = dateFormat.format(calendar.time)
        repository.getEarthQuakes(startTime = startTime, endTime = endtime){
//            it.features?.map { f->
//                dateHistory.format(f?.properties?.time?.toLong()?.let { it1 ->it1 * 1000L})
//        }
            val data  : MutableList<History> = mutableListOf()
            it.features?.forEach { f ->
                val date = dateHistory.format(f?.properties?.time?.toLong()?.let { it1 -> it1 * 1000L })
                val time = timeHistory .format(f?.properties?.time?.toLong()?.let { it1 -> it1 * 1000L })
                data += History(date = date, time = time, place = f?.properties?.place.toString(),mag = f?.properties?.mag.toString())

            }
            history = history.copy(data = data)
        }


    }
}

data class LocationHistory(
   val location: List<LatLng> = listOf(LatLng(0.0,0.0),

   ))
data class Histories(
val data : List<History> = listOf(

)
        )