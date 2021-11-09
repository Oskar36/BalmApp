package com.example.balmapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class NavFrag {
    companion object{
        fun replaceFragment(someFragment: Fragment?, f_activity: FragmentActivity, id:Int) {
            val fragment: Fragment =f_apodo()
            f_activity.supportFragmentManager.beginTransaction().replace(id, fragment).commit()
        }
        fun IniciarMapa(context: Context){
            val myIntent = Intent(context, a_mapa::class.java)
            context.startActivity(myIntent)
            (context as Activity).finish()
        }
    }
}