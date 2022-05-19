package com.example.livedata

import android.app.Application

class AccompaniedApp: Application(){
    companion object {
        lateinit var api: RetrofitServices
    }

    override fun onCreate() {
        super.onCreate()
        api = Common.retrofitService
    }
}