package com.example.siaga.model

import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapType
import kotlinx.coroutines.flow.MutableStateFlow

data class Location(
    val location: String,
    val address: String
)
interface LocationTracker {
    suspend fun getCurrentLocation():Location?
}
//data class Locations (
//    val isBuildingEnabled: Boolean = false,
//    val isIndoorEnabled: Boolean = false,
//    val isMyLocationEnabled: Boolean = false,
//    val isTrafficEnabled: Boolean = false,
//    val latLngBoundsForCameraTarget: LatLngBounds? = null,
//    val mapStyleOptions: MapStyleOptions? = null,
//    val mapType: MapType = MapType.NORMAL,
//    val maxZoomPreference: Float = 21.0f,
//    val minZoomPreference: Float = 3.0f
//)