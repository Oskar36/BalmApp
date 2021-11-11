package com.example.balmapp

import android.app.Activity
import android.content.Context
import android.content.Intent
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
    }
}