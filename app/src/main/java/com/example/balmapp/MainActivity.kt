package com.example.balmapp


import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.balmapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        SystemClock.sleep(1000)


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            return
        }
    }
//se controla la pulsación del boton atras
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    if(keyCode==KEYCODE_BACK){
        //creacion de pop up cerrar aplicacion.
        MaterialAlertDialogBuilder(applicationContext,
            R.style.Body_ThemeOverlay_MaterialComponents_MaterialAlertDialog)
            .setTitle("CERRAR")
            .setMessage("¿SALIR DE LA APLICACIÓN?")
            .setIcon(resources.getDrawable(R.drawable.logodantzaris1))
            .setPositiveButton("SI") { dialog, which ->

                val intent =  Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
            .setNegativeButton("NO") { dialog, which ->

                dialog.dismiss()
            }

            .show()


    }
    }
}