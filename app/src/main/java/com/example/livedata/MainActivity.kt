package com.example.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val mainAdapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MyViewModel by viewModels()
        model.getWeatherData().observe(this) { weatherData ->
            mainAdapter.submitList(weatherData)
        }

        findViewById<RecyclerView?>(R.id.rView).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mainAdapter
        }
    }


}