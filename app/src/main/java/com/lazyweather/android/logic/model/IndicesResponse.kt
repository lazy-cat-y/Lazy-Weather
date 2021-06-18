package com.lazyweather.android.logic.model

//生活指数
data class IndicesResponse(val code: String, val daily: List<DailyIndices>) {

    data class DailyIndices(
            val type:String,
            val name:String,
            val level:String,
            val category:String,
            val text:String
    )

}
