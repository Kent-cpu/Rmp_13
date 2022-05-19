package com.example.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livedata.AccompaniedApp.Companion.api
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class MyViewModel : ViewModel() {
    private val weatherData: MutableLiveData<List<ListItem>> by lazy {
        MutableLiveData<List<ListItem>>().also {
            runBlocking { launch { loadWeatherData() } }
        }
    }

    fun getWeatherData(): LiveData<List<ListItem>> {
        return weatherData
    }

    private suspend fun loadWeatherData() = coroutineScope {
        GlobalScope.launch {
            api.getWeatherList().enqueue(object : Callback<DataWeather> {
                override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                    if (response.isSuccessful) {
                        val dataWeather = response.body() as DataWeather
                        weatherData.value = dataWeather.list
                    }
                }

                override fun onFailure(call: Call<DataWeather>, t: Throwable) {
                }
            })
        }
    }
}