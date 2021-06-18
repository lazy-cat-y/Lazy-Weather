package com.lazyweather.android.logic.model

import com.lazyweather.android.R

class Sky(val info: String, val icon: Int, val bg: Int)

private val sky = mapOf(
    100 to Sky("晴", R.drawable.ic_clear_day, R.drawable.bg_clear_day),
    150 to Sky("晴", R.drawable.ic_clear_night, R.drawable.bg_clear_night),
    101 to Sky("多云", R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day),
    104 to Sky("多云", R.drawable.ic_partly_cloud_night, R.drawable.bg_partly_cloudy_night),
    300 to Sky("阴", R.drawable.ic_cloudy, R.drawable.bg_cloudy),
    301 to Sky("大风", R.drawable.ic_cloudy, R.drawable.bg_wind),
    302 to Sky("小雨", R.drawable.ic_light_rain, R.drawable.bg_rain),
    303 to Sky("中雨", R.drawable.ic_moderate_rain, R.drawable.bg_rain),
    305 to Sky("大雨", R.drawable.ic_heavy_rain, R.drawable.bg_rain),
    306 to Sky("暴雨", R.drawable.ic_storm_rain, R.drawable.bg_rain),
    307 to Sky("雷阵雨", R.drawable.ic_thunder_shower, R.drawable.bg_rain),
    310 to Sky("雨夹雪", R.drawable.ic_sleet, R.drawable.bg_rain),
    400 to Sky("小雪", R.drawable.ic_light_snow, R.drawable.bg_snow),
    401 to Sky("中雪", R.drawable.ic_moderate_snow, R.drawable.bg_snow),
    402 to Sky("大雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    403 to Sky("暴雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    404 to Sky("冰雹", R.drawable.ic_hail, R.drawable.bg_snow),
    501 to Sky("轻度雾霾", R.drawable.ic_light_haze, R.drawable.bg_fog),
    504 to Sky("中度雾霾", R.drawable.ic_moderate_haze, R.drawable.bg_fog),
    511 to Sky("重度雾霾", R.drawable.ic_heavy_haze, R.drawable.bg_fog),
    512 to Sky("雾", R.drawable.ic_fog, R.drawable.bg_fog),
    999 to Sky("浮尘", R.drawable.ic_fog, R.drawable.bg_fog)
)

fun getSky(skycon: Int): Sky{
    return sky[skycon] ?: sky[999]!!
}