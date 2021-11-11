package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LProcesionJuegoAdivinarPenitenteakBinding
import com.example.balmapp.databinding.LPuentePuzzleBinding


private var _binding: LProcesionJuegoAdivinarPenitenteakBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_juego_adivinarPenitenteak : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarPenitenteakBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener(){
            val fragment:Fragment=f_procesion_juego_adivinarAndreMariaBirjina()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.penitenteak)
    }
    override fun onResume() {

        super.onResume()


        binding.imgaudioplay1.setOnClickListener(){
            //Iniciamos el audio
            mediaplayer!!.start();
        }
        binding.imgaudiostop1.setOnClickListener(){
            //Pausamos el audio
            mediaplayer!!.pause();
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}