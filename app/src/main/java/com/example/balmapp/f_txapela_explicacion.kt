package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LTxapelaExplicacionBinding

private var _binding: LTxapelaExplicacionBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_txapela_explicacion : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LTxapelaExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgtxapelaExplicacionLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.boinas)
        //Iniciamos el audio
        mediaplayer!!.start()

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgtxapelaExplicacionLogo)
        }
        binding.btntxapelaExplicacionJugar.setOnClickListener{

            val fragment:Fragment=f_juego_txapela_unir()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Explicacion")
            //paramos el audio

        }
        //parar y continuar el audio
        binding.imgtxapelaExplicacionLogo.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgtxapelaExplicacionLogo)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imgtxapelaExplicacionLogo)
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mediaplayer!!.stop()
    }

}