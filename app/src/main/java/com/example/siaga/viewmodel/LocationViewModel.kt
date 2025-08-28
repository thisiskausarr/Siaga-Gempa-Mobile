package com.example.siaga.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import android.Manifest
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location
    var locationUiState by mutableStateOf(LocationData())
    fun requestLocation(context: Context) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                location?.let {
                    val geocoder = Geocoder(context)
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    if (addresses != null) {
                        if (addresses.isNotEmpty()) {
                            val address = addresses[0]
                            val knownAddress = "${address.thoroughfare}, ${address.locality}, ${address.adminArea}, ${address.countryName}"
                            // Do something with the known address, e.g. display it to the user
                            locationUiState = locationUiState.copy(
                                address = knownAddress,latitude=location.latitude, longitude=location.longitude)

                        }
                    }
                }
            }
        }
    }

    private  var job: Job? = null
    var locationAutofill = mutableStateListOf<AutocompleteResult>()

    fun searchPlaces(query: String,context: Context) {
        val apiKey = "AIzaSyB7EfCydTqqNe8D5FTZHx7__cW7yq6Q6IA"
        // Initialize the SDK
        Places.initialize(context, apiKey)

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(context)

        job?.cancel()
        locationAutofill.clear()
        job = viewModelScope.launch {
            val request = FindAutocompletePredictionsRequest
                .builder()
                .setQuery(query)
                .build()
            placesClient
                .findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    Log.d("Get Coordinates", "Place found: ${response.autocompletePredictions}")

                    locationAutofill += response.autocompletePredictions.map {
                        AutocompleteResult(
                            it.getFullText(null).toString(),
                            it.placeId
                        )
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    println(it.cause)
                    Log.d("error search place",it.cause.toString())

                    Log.d("error search place",it.message.toString())
                    println(it.message)
                }
        }
    }
    fun getCoordinates(result: AutocompleteResult,context:Context) {
        val apiKey = "AIzaSyB7EfCydTqqNe8D5FTZHx7__cW7yq6Q6IA"
        // Initialize the SDK
        Places.initialize(context, apiKey)

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(context)

        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(result.placeId, placeFields)
        placesClient.fetchPlace(request)
            .addOnSuccessListener {
                if (it != null) {
                    Log.d("Get Coordinates", "Place found: ${it.place.latLng}")
                    locationUiState= locationUiState.copy(latitude = it.place.latLng.latitude, longitude = it.place.latLng.longitude!!)

                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

}
data class AutocompleteResult(
    val address: String,
    val placeId: String
)
data class LocationData(
    val latitude: Double=0.0, val longitude: Double=0.0,
    val address:String = ""
)