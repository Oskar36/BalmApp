package com.example.balmapp

import android.app.Application

class Sharedapp : Application(){
    companion object{
        lateinit var prefs: Juego
        lateinit var gune: Gune
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Juego(applicationContext)
        gune = Gune(applicationContext)
    }
}