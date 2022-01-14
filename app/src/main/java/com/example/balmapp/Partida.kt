package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences


class Partida(context: Context) {
    private val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    private val SHARED_NAME = "shared_name3"
    private val Partida: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var partida: String
        //comprobamos el archivo de sahred preferences
        get() = Partida.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Partida.edit().putString(SHARED_NAME, value).apply()
}