package com.lazyweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.lazyweather.android.LazyWeatherApplication
import com.lazyweather.android.logic.model.Place

object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJosn = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJosn, Place::class.java)
    }

    fun isPlaceSaves() = sharedPreferences().contains("place")

    private fun sharedPreferences() = LazyWeatherApplication.context.getSharedPreferences("lazy_weather", Context.MODE_PRIVATE)

}