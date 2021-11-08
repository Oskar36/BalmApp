package com.example.balmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.balmapp.databinding.ActivityMainBinding
import com.example.balmapp.databinding.LMapaBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
    private fun cargarfragment(fragment: Fragment) {
        val transaction= supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}