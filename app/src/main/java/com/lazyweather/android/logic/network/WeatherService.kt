package com.lazyweather.android.logic.network

import com.lazyweather.android.LazyWeatherApplication
import com.lazyweather.android.logic.model.AirResponse
import com.lazyweather.android.logic.model.DailyResponse
import com.lazyweather.android.logic.model.IndicesResponse
import com.lazyweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

//    @GET("v2.5/${LazyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
//    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>
//
//    @GET("v2.5/${LazyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
//    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

    @GET("v7/weather/now?key=${LazyWeatherApplication.KEY}")
    fun getRealtimeWeather(@Query("location") _where: String): Call<RealtimeResponse>

    @GET("v7/weather/7d?key=${LazyWeatherApplication.KEY}")
    fun getDailyWeather(@Query("location") _where: String): Call<DailyResponse>

    @GET("v7/weather/24h?key=${LazyWeatherApplication.KEY}")
    fun getTiemWeather(@Query("location") _where: String): Call<DailyResponse>

    @GET("v7/air/now?key=${LazyWeatherApplication.KEY}")
    fun getNowAir(@Query("location") _where: String): Call<AirResponse>

    @GET("v7/indices/1d?type=1,2,3,9&key=${LazyWeatherApplication.KEY}")
    fun getIndices(@Query("location") _where: String): Call<IndicesResponse>

}