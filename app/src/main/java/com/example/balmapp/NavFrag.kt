package com.example.balmapp


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.util.*


class NavFrag {
    companion object{
        var theme=""
        var atras_lugar=""
        var idioma=""
        var contador=0
        var pantalla_inicio=true
        var terminado_unir=false
        var gune=0
        var modo_libre= mutableListOf<String>()
        fun replaceFragment(someFragment: Fragment, f_activity: FragmentActivity, id:Int,nombre:String?=null,nomdestino:String?=null) {
            val fragment: Fragment =someFragment
            if (nombre.equals("Repetir")){
                f_activity.supportFragmentManager.popBackStack(nomdestino,1)
            }
            else{
                f_activity.supportFragmentManager.beginTransaction().replace(id, fragment).addToBackStack(nombre).commit()
            }
        }
        fun IniciarActivity(context: Context,actividad:String){
            val nombreclase= "com.example.balmapp.$actividad"
            val clase = Class.forName(nombreclase)
            val intent = Intent(context, clase)
            context.startActivity(intent)
        }
        fun Abrirfragment(fragment: Fragment,activity: AppCompatActivity,layout: Int) {
            val currentNightMode: Int = activity.resources
                .configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

            if(theme=="" || idioma==""){
                val transaction= activity.supportFragmentManager.beginTransaction()
                transaction.add(layout, fragment)
                transaction.commit()
                when (currentNightMode) {
                    Configuration.UI_MODE_NIGHT_NO -> {
                        theme=currentNightMode.toString()
                    }
                    Configuration.UI_MODE_NIGHT_YES -> {
                        theme=currentNightMode.toString()
                    }
                }
            }else{
                if(theme!=currentNightMode.toString() || idioma!= Locale.getDefault().language){
                    theme=currentNightMode.toString()
                    idioma=Locale.getDefault().language
                }else{
                    val transaction= activity.supportFragmentManager.beginTransaction()
                    transaction.add(layout, fragment)
                    transaction.commit()
                }
            }
        }

        //hace que el logo se anime
        fun animacion_dantzaris(imagen: ImageView){
            val dantzaris = imagen.apply {
                setBackgroundResource(R.drawable.animaciondantzaris)
                animacion = background as AnimationDrawable
            }
            animacion.start()

        }
        //animacion de la cuenta atras de el puchero
        fun animacion_numero(imagen: ImageView){
            val numero = imagen.apply {
                setBackgroundResource(R.drawable.animacion_numeros)
                animacion = background as AnimationDrawable
            }
            animacion.start()

        }

        //para la animacion de los dantzaris
        fun animacion_dantzaris_parar(imagen: ImageView){
            imagen.apply {
                setBackgroundResource(R.drawable.animaciondantzaris)
                animacion = background as AnimationDrawable
            }

            animacion.stop()

        }

        fun MarcadorJuegofin(gune: String):Fragment{
            var fragment:Fragment?=null
            when (gune){
                "1.Gunea" ->     fragment=f_puente_puzzle()
                "2.Gunea 2" ->   fragment=f_kolitzajuego()
                "3.Gunea 2" ->   fragment=f_jauregi_unirjuego()
                "4.Gunea 2" ->   fragment=f_procesion_ordenar()
                "5.Gunea" ->     fragment=f_juego_txapela_unir()
                "6.Gunea" ->     fragment=f_sanfelipe_cancion()
                "7.Gunea" ->     fragment=f_putxero_juego()
            }
            return fragment!!
        }

        fun AbrirSiguiente(gune: String):Fragment {
            var fragment:Fragment?=null
            when (gune){
                "2.Gunea 1" ->   fragment=f_kolitzajuego()
                "3.Gunea 1" ->   fragment=f_jauregi_unirjuego()
                "4.Gunea 1" ->   fragment=f_procesion_ordenar()
                "2.Gunea 2" ->   fragment=f_kolitzajuego()
                "3.Gunea 2" ->   fragment=f_jauregi_unirjuego()
                "4.Gunea 2" ->   fragment=f_procesion_ordenar()
            }
            return fragment!!
        }

        //te lleva al siguiente juego
        fun  MarcadorJuegofinintermedio(gune: String):Fragment{
            var fragment:Fragment?=null
            when (gune){
                "2.Gunea 1" ->   fragment=f_kolitza_juego_sopaletras()
                "3.Gunea 1" ->   fragment=f_jauregi_puzzle()
                "4.Gunea 1" ->   fragment=f_procesion_juego_adivinar_Jesus()
                "2.Gunea 2" ->   fragment=f_kolitza_juego_sopaletras()
                "3.Gunea 2" ->   fragment=f_jauregi_puzzle()
                "4.Gunea 2" ->   fragment=f_procesion_juego_adivinar_Jesus()
            }
            return fragment!!
        }
        fun EleccionJuego(radioubutton: RadioButton, fragment: Fragment, activity: FragmentActivity, view: View, context: Context){
            if(radioubutton.isChecked){
                replaceFragment(fragment,activity,((view as ViewGroup).parent as View).id,"Juego1","Explicacion")
            }else{
                Toast.makeText(context, R.string.error_toast, Toast.LENGTH_SHORT).show()
            }
        }

        //dialogo fin de juego
        fun mostrarDialogoPersonalizado(layoutInflater:LayoutInflater,context: Context,activity:FragmentActivity,view: View){

            AlertDialog.Builder(context, R.style.DialogBasicCustomStyle)
                .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
                .setPositiveButton(R.string.txt_finalizar,
                    DialogInterface.OnClickListener { dialog, id ->
                        //temina el juego y te lleva a  el mapa
                        if(NavFrag.modo_libre.size!=0 && Sharedapp.partida.partida =="libre"){
                            if (!modo_libre.contains(juego_modo_libre(Sharedapp.gune.gune).trim())){
                                modo_libre.add(juego_modo_libre(Sharedapp.gune.gune).trim())
                            }
                        }else{
                            modo_libre.add(juego_modo_libre(Sharedapp.gune.gune).trim())
                        }
                        NavFrag.IniciarActivity(context,"a_mapa")
                        activity.finish()
                        // sign in the user ...
                    })
                .setNeutralButton(R.string.repetir,
                    //repite la pantalla en la que esta
                    DialogInterface.OnClickListener { dialog, id ->
                        val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                        replaceFragment(fragment,activity,view.id)
                        // sign in the user ...
                    })
                .setCancelable(false)
                .create()
                .show()

        }
        private fun juego_modo_libre(juego:String):String{
            var gune=""
            when (juego){
                "2.Gunea 2" ->   gune="2.Gunea"
                "3.Gunea 2" ->   gune="3.Gunea"
                "4.Gunea 2" ->   gune="4.Gunea"
            }

            return gune.trim()
        }
    }
}