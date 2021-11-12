package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LProcesionJuegoAdivinarAndremariabirjinaBinding


private var _binding: LProcesionJuegoAdivinarAndremariabirjinaBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_juego_adivinarAndreMariaBirjina : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarAndremariabirjinaBinding.inflate(inflater, container, false)
        return  binding.root

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener(){
            val fragment:Fragment=f_procesion_ordenar()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }

    }
    override fun onResume() {

        super.onResume()
        mediaPlayer = MediaPlayer.create(context, R.raw.andremariabirjina)
        binding.imgaudioplay1.setOnClickListener{

            if(mediaPlayer!!.isPlaying()){
                mediaPlayer!!.seekTo(0)
            } else {
                mediaPlayer!!.start()
            }
        }
        binding.imgaudiostop1.setOnClickListener{
            mediaPlayer!!.pause()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaPlayer!!.stop()
    }
}