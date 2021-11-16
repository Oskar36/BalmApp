package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences

class Gune(context: Context) {
    val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    val SHARED_NAME = "shared_name"
    val Gune: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var gune: String
        //comprobamos el archivo de sahred preferences
        get() = Gune.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Gune.edit().putString(SHARED_NAME, value).apply()
}