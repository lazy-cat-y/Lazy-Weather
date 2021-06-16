package com.lazyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class LazyWeatherApplication: Application() {

    companion object {
        const val TOKEN = "6OzMEZ3RPJRerjwC"        //令牌
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}