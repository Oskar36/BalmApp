package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences

class Juego(context: Context) {
    private val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    private val SHARED_NAME = "juego"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var juego: String
        //comprobamos el archivo de sahred preferences
        get() = prefs.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}