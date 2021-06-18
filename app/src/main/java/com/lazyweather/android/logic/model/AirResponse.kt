package com.lazyweather.android.logic.model

//空气质量
class AirResponse(val code: String, val now: NowAir) {

    class NowAir(
            val aqi:String,
            val category:String,
            val primary:String,
            val pm10:String,
            val pm2p5:String,
            val no2:String,
            val so2:String,
            val co:String,
            val o3:String
    )

}