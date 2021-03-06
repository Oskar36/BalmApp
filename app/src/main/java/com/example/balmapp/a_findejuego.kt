package com.example.balmapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.balmapp.databinding.LFindejuegoBinding

private var mediaplayer: MediaPlayer? = null
private lateinit var binding: LFindejuegoBinding
class a_findejuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //asignar layout
        binding = LFindejuegoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //inicio de animación y audio
        NavFrag.animacion_dantzaris(binding.imageButton)
        mediaplayer = MediaPlayer.create(this, R.raw.pertsonaia)
        //para la animación cuando se acaba el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imageButton)
        }
        //para la animación y continuar si se para o reanuda el audio
        binding.imageButton.setOnClickListener{
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imageButton)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imageButton)
            }
        }
        //empezar una nueva partida
        binding.btnrepetir.setOnClickListener{
            BD.insertarNuevaPartida(Sharedapp.nombre.nombre)
            BD.cargarPartida(Sharedapp.nombre.nombre)
            NavFrag.IniciarActivity(this,"a_mapa")
            finish()
        }
        //volver a la pantalla de inicio
        binding.btnfin.setOnClickListener{
            NavFrag.IniciarActivity(this,"MainActivity")
            finish()
        }
    }
    // al darle atras abre la pantalla principal
    override fun onBackPressed() {
        super.onBackPressed()
        NavFrag.IniciarActivity(this,"MainActivity")
        finish()
    }
}