package com.example.balmapp

import android.app.Application

class Sharedapp : Application(){
    companion object{
        lateinit var prefs: Juego
        lateinit var gune: Gune
        lateinit var usuario: Usuario
        lateinit var nombre:Nombre
        lateinit var partida:Partida

    }
    override fun onCreate() {
        super.onCreate()
        prefs = Juego(applicationContext)
        gune = Gune(applicationContext)
        usuario=Usuario(applicationContext)
        nombre=Nombre(applicationContext)
        partida=Partida(applicationContext)
    }
}