package com.example.siaga.model

import com.google.android.gms.maps.model.LatLng


data class Donation(
    val id:String ="",
    val image: String ="",
    val title: String="",
    val location: LatLng = LatLng(0.0,0.0) ,
    val nominal:Int =0,
    val target:Int =0
)

