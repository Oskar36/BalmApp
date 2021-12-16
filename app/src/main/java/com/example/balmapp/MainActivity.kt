package com.example.balmapp


import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isInvisible
import com.example.balmapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*


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

        val currentNightMode: Int = this.resources
            .configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        NavFrag.theme=currentNightMode.toString()
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            return
        }
        NavFrag.idioma=Locale.getDefault().language
    }
//se controla la pulsación del boton atras
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

    var cerrar1 = false
    android.app.AlertDialog.Builder(this, R.style.DialogBasicCustomStyle)
        .setView(layoutInflater.inflate(R.layout.l_dialogo_cerrar, null))
        .setPositiveButton(R.string.si,
            DialogInterface.OnClickListener { dialog, id ->
                finish()
                cerrar1 = super.onKeyDown(keyCode, event)
            })
        .setNeutralButton(R.string.cancel,
            DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
            })
        .setCancelable(false)
        .create()
        .show()
        return cerrar1
    }



}