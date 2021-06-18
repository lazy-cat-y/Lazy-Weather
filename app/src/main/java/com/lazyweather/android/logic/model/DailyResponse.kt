package com.lazyweather.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

//预测
data class DailyResponse(val code: String, val daily: List<Daily>) {

//    data class Result(val daily: Daily)
//
//    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>,
//        @SerializedName("life_index") val lifeIndex: LifeIndex)
//
//    data class Temperature(val max: Float, val min: Float)
//
//    data class Skycon(val value: String, val date: Date)
//
//    data class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>,
//                         val ultraviolet: List<LifeDescription>,
//                         val dressing: List<LifeDescription>)
//
//    data class LifeDescription(val desc: String)

    class Daily(
            val fxDate:String,
            val sunrise:String,
            val sunset:String,
            val moonrise:String,
            val moonset:String,
            val moonPhase:String,
            val tempMax:String,
            val tempMin:String,
            val iconDay:String,
            val textDay:String,
            val iconNight:String,
            val textNight:String,
            val humidity:String,
            val precip:String,
            val pressure:String,
            val vis:String,
            val cloud:String,
            val uvIndex:String//紫外线度
    )

}