package com.lazyweather.android.logic.model

data class Weather(
        val realtime: RealtimeResponse.Now,
        val daily: List<DailyResponse.Daily>,
        val air: AirResponse.NowAir,
        val indices: List<IndicesResponse.DailyIndices>
)