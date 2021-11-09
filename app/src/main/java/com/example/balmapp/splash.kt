package com.example.balmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock


class splash : AppCompatActivity() {
    //Este codigo sirve para que aprezca el logo al inicar la app

    override fun onCreate(savedInstanceState: Bundle?) {
        SystemClock.sleep(1000)
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}