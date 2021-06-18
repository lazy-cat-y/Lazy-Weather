package com.lazyweather.android.logic

import androidx.lifecycle.liveData
import com.lazyweather.android.logic.dao.PlaceDao
import com.lazyweather.android.logic.model.Place
import com.lazyweather.android.logic.model.Weather
import com.lazyweather.android.logic.network.LazyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext


object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = LazyWeatherNetwork.searchPlaces(query)
        if (placeResponse.code == "200") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.code}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {

            val _where = "$lng,$lat"

            val deferredRealtime = async {
                LazyWeatherNetwork.getRealtimeWeather(_where)
            }
            val deferredDaily = async {
                LazyWeatherNetwork.getDailyWeather(_where)
            }
            val deferredAir = async {
                LazyWeatherNetwork.getNowAir(_where)
            }
            val deferredIndices = async {
                LazyWeatherNetwork.getIndices(_where)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            val airResponse = deferredAir.await()
            val indicesResponse = deferredIndices.await()
            if (realtimeResponse.code == "200" && dailyResponse.code == "200" && airResponse.code == "200" && indicesResponse.code == "200") {
                val weather = Weather(realtimeResponse.now, dailyResponse.daily, airResponse.now, indicesResponse.daily)
                Result.success(weather)
            } else {
                Result.failure(
                        RuntimeException(
                                "realtime response status is ${realtimeResponse.code}" +
                                        "daily response status is ${dailyResponse.code}"
                        )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaves()

}