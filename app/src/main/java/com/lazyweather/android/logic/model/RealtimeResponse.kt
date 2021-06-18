package com.lazyweather.android.logic.model


import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val code: String, val now: Now) {

//    data class Result(val realtime: Realtime)
//
//    data class Realtime(val skycon: String, val temperature: Float,
//                        @SerializedName("air_quality") val airQuality: AirQuality)
//
//    data class AirQuality(val aqi: AQI)
//
//    data class AQI(val chn: Float)

    data class Now(
            val temp:String,
            val feelsLike:String,
            val icon:String,
            val text:String,
            val humidity:String,
            val precip:String,
            val pressure:String,
            val vis:String,
            val cloud:String,
            val wind360:String,
            val windDir:String,
            val windScale:String,
            val windSpeed:String
    )

}