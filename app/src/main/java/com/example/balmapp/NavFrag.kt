package com.example.balmapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.core.content.ContextCompat.startActivity




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
                (context as Activity).finish()
        }
        fun cargarfragment(fragment: Fragment,activity: AppCompatActivity,layout: Int) {
            val transaction= activity.supportFragmentManager.beginTransaction()
            transaction.replace(layout, fragment).addToBackStack(null)
            transaction.commit()
        }
        fun addFragment(someFragment: Fragment, f_activity: FragmentActivity, id:Int) {
            val fragment: Fragment =someFragment
            f_activity.supportFragmentManager.beginTransaction().add(id, fragment).addToBackStack(null).commit()
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


