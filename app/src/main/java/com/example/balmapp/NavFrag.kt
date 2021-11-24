package com.example.balmapp


import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


class NavFrag {
    companion object{
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
            val transaction= activity.supportFragmentManager.beginTransaction()
            transaction.add(layout, fragment)
            transaction.commit()
        }
        fun animacion_dantzaris(imagen: ImageView){
            val dantzaris = imagen.apply {
            setBackgroundResource(R.drawable.animaciondantzaris)
            animacion = background as AnimationDrawable
        }
            animacion.start()

        }
        fun animacion_dantzaris_parar(imagen: ImageView){
            val dantzaris = imagen.apply {
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
                "3.Gunea 2" ->   fragment=f_procesion_ordenar()
                "4.Gunea 2" ->   fragment=f_procesion_ordenar()
            }
            return fragment!!
        }
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



    }

}


