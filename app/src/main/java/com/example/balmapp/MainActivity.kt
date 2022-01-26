package com.example.balmapp


import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.balmapp.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //carga el fragment de modo
        val fragment= f_modo()
        NavFrag.Abrirfragment(fragment,this,R.id.fl_main)

        SystemClock.sleep(1000)

        val currentNightMode: Int = this.resources
            .configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        NavFrag.theme=currentNightMode.toString()
        //pide los permisos que necesita la aplicacion

        NavFrag.idioma=Locale.getDefault().language
    }
    //se controla la pulsación del boton atras en el caso que si cierra la aplicacion  en caso que no, no pasa nada
    override fun onBackPressed() {
        if (NavFrag.pantalla_inicio){
            android.app.AlertDialog.Builder(this, R.style.DialogBasicCustomStyle)
                .setView(layoutInflater.inflate(R.layout.l_dialogo_cerrar, null))
                .setPositiveButton(R.string.si,
                    DialogInterface.OnClickListener { dialog, id ->
                        finish()
                    })
                .setNeutralButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
                .setCancelable(false)
                .create()
                .show()
        }else{
            super.onBackPressed()
        }
    }
}