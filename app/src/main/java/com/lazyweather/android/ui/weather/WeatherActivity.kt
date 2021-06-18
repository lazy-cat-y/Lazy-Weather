package com.lazyweather.android.ui.weather

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lazyweather.android.R
import com.lazyweather.android.logic.model.Weather
import com.lazyweather.android.logic.model.getSky
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.forecast.*
import kotlinx.android.synthetic.main.life_item.*
import kotlinx.android.synthetic.main.now.*
import java.text.SimpleDateFormat
import java.util.*


class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this)[WeatherViewModel::class.java] }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        /**
         * Google将此用法弃用
         * val decorView = window.decorView
         * decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
         *
         * 使用WindowCompat.setDecorFitsSystemWindows(window, false) 使用于android10上此函数会导致按钮和城市名称消失
         * window.setDecorFitsSystemWindows(false)在真机上崩溃，虚拟机上可以
         *
         */
//        window.setDecorFitsSystemWindows(false)
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window?.run {
//            WindowCompat.setDecorFitsSystemWindows(this, false)
//        }
//        val decorView = window.decorView
//        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_weather)
        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            swipeRefresh.isRefreshing = false
        })
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        refreshWeather()
        swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)

        navBtn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener{

            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(drawerView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }

        })
    }

    fun refreshWeather() {
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        swipeRefresh.isRefreshing = true
    }

    private fun showWeatherInfo(weather: Weather) {
//        var bindingNow = NowBinding.inflate(layoutInflater)
//        var bindingForecast = ForecastBinding.inflate(layoutInflater)
//        var bindingLifeItem = LifeItemBinding.inflate(layoutInflater)
//        var binding = ActivityWeatherBinding.inflate(layoutInflater)
        placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        val air = weather.air
        val indices = weather.indices
        val currentTempText = "${realtime.temp.toInt()} ℃"
        currentTemp.text = currentTempText
        currentSky.text = getSky(realtime.icon.toInt()).info
        val currentPM25Text = "空气指数 ${air.aqi}"
        currentAQI.text = currentPM25Text
        nowLayout.setBackgroundResource(getSky(realtime.text.toInt()).bg)
        val days = daily.size
        for (i in 0 until days) {
            val skycon = daily[i].iconDay
            val temperature = daily[i].tempMax
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon)
            val sky = getSky(skycon.toInt())
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${daily[i].tempMin.toInt()} ~ ${daily[i].tempMax.toInt()} ℃"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }
        val lifeIndex = indices
        coldRiskText.text = lifeIndex[9].category
        dressingText.text = lifeIndex[3].category
        ultravioletText.text = lifeIndex[5].category
        carWashingText.text = lifeIndex[2].category
        weatherLayout.visibility = View.VISIBLE
    }

}