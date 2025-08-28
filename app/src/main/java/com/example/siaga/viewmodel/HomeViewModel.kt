package com.example.siaga.viewmodel

import android.icu.util.Calendar
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siaga.model.FeaturesItem
import com.example.siaga.model.UsgsEarthQuake
import com.example.siaga.repository.EarthQuakeRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class   HomeViewModel(
    private val repository: EarthQuakeRepository = EarthQuakeRepository()
) : ViewModel() {
    var mapstate by mutableStateOf(MapsUiState())
    fun launchEarthQuake() =viewModelScope.launch{
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val timeformat = SimpleDateFormat("hh:mm a")
            val endtime = dateFormat.format(Calendar.getInstance().time)
            val calendar = Calendar.getInstance()

            calendar.add(Calendar.MONTH, -1)

            val startTime = dateFormat.format(calendar.time)
var earthQuake: UsgsEarthQuake?=null
            Log.d("EarthQuakeRepository", "getEarthQuakes: $startTime")
            Log.d("EarthQuakeRepository", "getEarthQuakes: $endtime")
            repository.getEarthQuakes(startTime=startTime,endTime=endtime){it->
                earthQuake = it
            }
            val earthQuakeNewests =
                earthQuake?.features?.sortedByDescending { it?.properties?.time }?.get(0)
            mapstate= mapstate.copy(
                location =
                LatLng(earthQuakeNewests?.geometry?.coordinates?.get(1)!!,earthQuakeNewests.geometry.coordinates.get(0)!!),
                time = timeformat.format(earthQuakeNewests.properties?.time ?: (0 * 1000L)),
                mag = earthQuakeNewests.properties?.mag!!,
                depth = earthQuakeNewests.geometry.coordinates[2]!!,
                distance = earthQuakeNewests.properties.place.toString().split(" ")[0],
                tsunami = earthQuakeNewests.properties.tsunami ===1,
                place = earthQuakeNewests.properties.place,

            )


        }catch (e:Exception){
            Log.d("HomeViewModel",e.message.toString())
        }
    }

}

data class MapsUiState(
    var location: LatLng = LatLng(0.0, 0.0),
    var time: String? =null,
    var mag : Double = 0.0,
    var depth : Double = 0.0,
    var distance : String? =null,
    var tsunami: Boolean= false,
    var place: String? =null,
)