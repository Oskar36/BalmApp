package com.example.balmapp

import android.app.Application

class Sharedapp : Application(){
    //Se crean las variables de los diferentes datos que queremos guardar
    companion object{
        lateinit var prefs: Juego
        lateinit var gune: Gune
        lateinit var usuario: Usuario
        lateinit var nombre:Nombre
        lateinit var partida:Partida
        lateinit var menu:Menu
    }
    //Se inicializan las variables teniendo en  cuenta el contexto de la aplicacción
    override fun onCreate() {
        super.onCreate()
        prefs = Juego(applicationContext)
        gune = Gune(applicationContext)
        usuario=Usuario(applicationContext)
        nombre=Nombre(applicationContext)
        partida=Partida(applicationContext)
        menu=Menu(applicationContext)
    }
}