package com.example.balmapp

import android.content.Context
import android.content.SharedPreferences

class Menu(context: Context) {
    private val PREFS_NAME = "com.example.balmapp.sharedpreferences"
    private val SHARED_NAME = "shared_name4"
    private val Menu: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var menu: String
        //comprobamos el archivo de sahred preferences
        get() = Menu.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Menu.edit().putString(SHARED_NAME, value).apply()
}