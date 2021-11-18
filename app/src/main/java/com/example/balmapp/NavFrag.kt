package com.example.balmapp


import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity





class NavFrag {
    companion object{
        fun replaceFragment(someFragment: Fragment, f_activity: FragmentActivity, id:Int) {
            val fragment: Fragment =someFragment
            f_activity.supportFragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit()
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
    }
}


