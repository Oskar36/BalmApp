package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences

class Usuario(context: Context) {
    private val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    private val SHARED_NAME = "usuario"
    private val Usuario: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var usuario: String
        //comprobamos el archivo de sahred preferences
        get() = Usuario.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Usuario.edit().putString(SHARED_NAME, value).apply()
}