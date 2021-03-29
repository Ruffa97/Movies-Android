package com.example.cinemex

import android.content.SharedPreferences

object DataManager {
    private lateinit var sharedPrefs: SharedPreferences

    private const val TIME_LIMIT_KEY = "timeLimitKey"

    const val SHARED_PREFERENCES_KEY = "sharedPreferencesKey"

    fun init(sharedPreferences: SharedPreferences){
        sharedPrefs = sharedPreferences
    }

    var timeLimit
        get() = sharedPrefs.getInt(TIME_LIMIT_KEY,180)
        set(value) {
            sharedPrefs.edit().putInt(TIME_LIMIT_KEY,value).apply()
        }

}