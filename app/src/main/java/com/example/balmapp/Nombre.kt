package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences

class Nombre(context: Context) {
    private val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    private val SHARED_NAME = "nombre"
    private val Nombre: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var nombre: String
        //comprobamos el archivo de sahred preferences
        get() = Nombre.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Nombre.edit().putString(SHARED_NAME, value).apply()
}