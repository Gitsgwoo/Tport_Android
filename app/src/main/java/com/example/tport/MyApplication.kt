package com.example.tport

import android.app.Application
import com.example.tport.di.appModule
import com.example.tport.network.PathDatabase
import com.naver.maps.map.NaverMapSdk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    val database: PathDatabase by lazy { PathDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("ugyqtf6hr5")
    }
}