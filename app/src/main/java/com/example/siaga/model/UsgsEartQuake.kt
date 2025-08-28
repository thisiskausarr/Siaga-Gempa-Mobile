package com.example.siaga.model

import com.google.gson.annotations.SerializedName

data class UsgsEarthQuake(

    @field:SerializedName("features")
    val features: List<FeaturesItem?>? = null,
)

data class Geometry(

    @field:SerializedName("coordinates")
    val coordinates: List<Double?>? = null,

)

data class Properties(


    @field:SerializedName("magType")
    val magType: String? = null,

    @field:SerializedName("mag")
    val mag: Double? = null,

    @field:SerializedName("tsunami")
    val tsunami: Int? = null,


    @field:SerializedName("place")
    val place: String? = null,




    @field:SerializedName("time")
    val time: Long? = null,
    var timeHistory: String?
)

data class FeaturesItem(

    @field:SerializedName("geometry")
    val geometry: Geometry? = null,


    @field:SerializedName("properties")
    val properties: Properties? = null,


)
