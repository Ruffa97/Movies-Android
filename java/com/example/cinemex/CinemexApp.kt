package com.example.cinemex

import android.app.Application
import android.content.Context
import com.example.cinemex.dependnecyinjection.app.AppComponent
import com.example.cinemex.dependnecyinjection.app.AppModule
import com.example.cinemex.dependnecyinjection.app.DaggerAppComponent

class CinemexApp: Application() {

    public val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        DataManager.init(getSharedPreferences(DataManager.SHARED_PREFERENCES_KEY,Context.MODE_PRIVATE))
    }
}
