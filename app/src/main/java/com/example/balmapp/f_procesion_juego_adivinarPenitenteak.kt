package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.balmapp.databinding.LProcesionJuegoAdivinarPenitenteakBinding

private var _binding: LProcesionJuegoAdivinarPenitenteakBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_juego_adivinarPenitenteak : Fragment() {
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarPenitenteakBinding.inflate(inflater, container, false)
        return  binding.root
    }
    //Cuando demos al boton FINALIZAR nos llevara a la tercera parte del juego
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.atras_lugar=""
        binding.btnfinalizar.setOnClickListener {
            val fragment: Fragment = f_procesion_juego_adivinarAndreMariaBirjina()
            NavFrag.EleccionJuego(binding.penitenteRadio,fragment,requireActivity(),requireView(),requireContext())
        }
    }
    override fun onResume() {
        super.onResume()

        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.penitenteak)
        binding.imgaudioplay1.setOnClickListener{
        //controlamos que el audio no esta reproduciendose
            if(mediaplayer!!.isPlaying){
                mediaplayer!!.seekTo(0)
            } else {
                //Iniciamos el audio
                mediaplayer!!.start()
            }
        }
        binding.imgaudiostop1.setOnClickListener{
            //Pausamos el audio
            if(mediaplayer!!.isPlaying){
                mediaplayer!!.pause()
            }
        }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaplayer!!.stop()
    }
}