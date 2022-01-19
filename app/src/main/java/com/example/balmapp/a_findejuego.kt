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
        binding = LFindejuegoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        NavFrag.animacion_dantzaris(binding.imageButton)
        mediaplayer = MediaPlayer.create(this, R.raw.pertsonaia)



        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imageButton)
        }
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

    }
}