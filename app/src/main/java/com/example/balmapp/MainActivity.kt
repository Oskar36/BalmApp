package com.example.balmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.balmapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val fragment= f_modo()
        cargarfragment(fragment)

       binding.toolbar.inflateMenu(R.menu.menu)

    }
    private fun cargarfragment(fragment: Fragment) {
        val transaction= supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_main, fragment)
        transaction.commit()
    }

}