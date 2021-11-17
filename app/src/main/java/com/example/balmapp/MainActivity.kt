package com.example.balmapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        NavFrag.Abrirfragment(fragment,this,R.id.fl_main)



    }
}