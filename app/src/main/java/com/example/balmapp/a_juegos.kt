package com.example.balmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class a_juegos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //asignacion del layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_juegos)
        //iniciar el metodo
        seleccionjuego()
    }
    //carga el fragment corespondiente
    private fun seleccionjuego(){
        val juego:String=Sharedapp.prefs.juego
        var fragment: Fragment? =null
        when (juego){
            "puente" ->    fragment=f_puente_explicacion()
            "kolitza"->    fragment=f_monte_explicacion()
            "jauregi" ->   fragment=f_jauregi_explicacion()
            "procesion"->  fragment=f_procesion_explicacion()
            "san felipe"-> fragment=f_felipe_explicacion()
            "puchero" ->   fragment=f_putxero_explicacion()
            "boina" ->     fragment=f_txapela_explicacion()
        }
        NavFrag.Abrirfragment(fragment!!,this,R.id.framejuegos)
    }
//controlamos el boton atras si estas en el juego 1 te lleva a la explicacion y si estas en el juego 2 te lleva al primer juego
    override fun onBackPressed() {
        super.onBackPressed()
        if(NavFrag.atras_lugar=="Juego1"){
           supportFragmentManager.popBackStack("Explicacion",1)
        }else if(NavFrag.atras_lugar=="Juego2"){
            supportFragmentManager.popBackStack("Juego1",1)
       }
    }
}