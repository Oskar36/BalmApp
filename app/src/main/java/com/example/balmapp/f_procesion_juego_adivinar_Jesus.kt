package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LProcesionJuegoAdivinarJesusBinding


private var _binding: LProcesionJuegoAdivinarJesusBinding? = null
private val binding get() = _binding!!
private var mediaplayerexp: MediaPlayer? = null
class f_procesion_juego_adivinar_Jesus : Fragment() {
    private var mediaplayer: MediaPlayer? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgprocesionExplicacionLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayerexp = MediaPlayer.create(context, R.raw.procesion_exp_juego1)
        //Iniciamos el audio
        mediaplayerexp!!.start()

        //parar y continuar el audio
        binding.imgprocesionExplicacionLogo.setOnClickListener {
            if(mediaplayerexp!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgprocesionExplicacionLogo)
                mediaplayerexp!!.pause()
            }else{
                if(mediaplayerexp!!.currentPosition!=0 && mediaplayerexp!!.currentPosition!= mediaplayerexp!!.duration){
                    mediaplayerexp!!.seekTo(mediaplayerexp!!.currentPosition)
                }
                mediaplayerexp!!.start()
                NavFrag.animacion_dantzaris(binding.imgprocesionExplicacionLogo)
            }
        }
        //parar animacion cuando pare el audio
        mediaplayerexp!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgprocesionExplicacionLogo)
        }
        binding.btnfinalizar.setOnClickListener{
            mediaplayerexp!!.stop()
            val fragment:Fragment=f_procesion_juego_adivinarPenitenteak()
            NavFrag.EleccionJuego(binding.jesusRadio,fragment,requireActivity(),requireView(),requireContext())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarJesusBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mediaplayerexp!!.stop()
    }

    override fun onResume() {
        super.onResume()
        mediaplayer = MediaPlayer.create(context, R.raw.jesus)
        binding.imgaudioplay1.setOnClickListener{

            if(mediaplayer!!.isPlaying){
                mediaplayer!!.seekTo(0)
            } else {
                mediaplayer!!.start()
            }
        }
        binding.imgaudiostop1.setOnClickListener{
            if(mediaplayer!!.isPlaying){
                mediaplayer!!.pause()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //liberacion del productor de medios
        mediaplayerexp?.release()
        mediaplayerexp = null
    }


    }


