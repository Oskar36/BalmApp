package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences

class Juego(context: Context) {
    val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var juego: String
        //comprobamos el archivo de sahred preferences
        get() = prefs.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}