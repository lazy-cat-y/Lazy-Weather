package com.lazyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class LazyWeatherApplication: Application() {

    companion object {
        const val TOKEN = "6OzMEZ3RPJRerjwC"        //令牌
        const val KEY = "3a795a17fae44eaebf4f75ba9b49b552"  //和风的KEY
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}