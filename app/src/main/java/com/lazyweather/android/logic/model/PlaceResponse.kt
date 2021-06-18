package com.lazyweather.android.logic.model

data class PlaceResponse(val code: String, val places: List<Place>)

data class Place(
        val name: String,
        val id:String,
        val lat:String,
        val lon:String,
        val adm2:String,
        val adm1:String,
        val country:String
)

data class Location(val lng: String, val lat: String)