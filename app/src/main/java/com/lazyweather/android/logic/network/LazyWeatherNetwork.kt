package com.lazyweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object LazyWeatherNetwork {

    private val weatherService = ServiceCreatorWeather.create(WeatherService::class.java)

    suspend fun getDailyWeather(_where: String) = weatherService.getDailyWeather(_where).await()

    suspend fun getRealtimeWeather(_where: String) = weatherService.getRealtimeWeather(_where).await()

    suspend fun getNowAir(_where: String) = weatherService.getNowAir(_where).await()

    suspend fun getIndices(_where: String) = weatherService.getIndices(_where).await()

    private val placeService = ServiceCreatorPlace.create(PlaceService::class.java)

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {  continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                            RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}