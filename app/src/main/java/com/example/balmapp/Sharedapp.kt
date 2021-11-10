package com.example.balmapp

import android.app.Application

class Sharedapp : Application(){
    companion object{
        lateinit var prefs: Juego
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Juego(applicationContext)
    }
}