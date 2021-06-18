package com.lazyweather.android.logic.network

import com.lazyweather.android.LazyWeatherApplication
import com.lazyweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/city/lookup?key=${LazyWeatherApplication.KEY}")
    fun searchPlaces(@Query("location") query: String): Call<PlaceResponse>

}