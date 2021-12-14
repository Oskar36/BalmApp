package com.example.balmapp

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LTxapelaUnirBinding


private var _binding:LTxapelaUnirBinding? = null
private val binding get() = _binding!!
private var mediaplayeraudio1: MediaPlayer? = null
private var mediaplayeraudio2: MediaPlayer? = null
private var mediaplayeraudio3: MediaPlayer? = null
private var mediaplayeraudio4: MediaPlayer? = null
private var mediaplayer_azal: MediaPlayer? = null

class f_juego_txapela_unir : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LTxapelaUnirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnsiguiente.setOnClickListener{
            val fragment:Fragment=f_txapelaunir2()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Juego1")
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)


        //parar animacion cuando pare el audio
        //    mediaplayer!!.setOnCompletionListener {
         //   NavFrag.animacion_dantzaris_parar(binding.imglogo)        }

        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer_azal = MediaPlayer.create(context, R.raw.azalpena_unir_txapela)
        //Iniciamos el audio
        mediaplayer_azal!!.start()

        //parar animacion cuando pare el audio
        mediaplayer_azal!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)
        }
        //parar y continuar el audio
        binding.imglogo.setOnClickListener {
            if(mediaplayer_azal!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imglogo)
                mediaplayer_azal!!.pause()
            }else{
                if(mediaplayer_azal!!.currentPosition!=0 && mediaplayer_azal!!.currentPosition!= mediaplayer_azal!!.duration){
                    mediaplayer_azal!!.seekTo(mediaplayer_azal!!.currentPosition)
                }
                mediaplayer_azal!!.start()
                NavFrag.animacion_dantzaris(binding.imglogo)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        mediaplayeraudio1 = MediaPlayer.create(context, R.raw.txapelaaudio1)
        mediaplayeraudio2 = MediaPlayer.create(context, R.raw.txapelaaudio2)
        mediaplayeraudio3 = MediaPlayer.create(context, R.raw.txapelaaudio3)
        mediaplayeraudio4 = MediaPlayer.create(context, R.raw.txapelaaudio4)


        //funcion para controlar audio1
        binding.playaudio1.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio1!!.isPlaying){
                mediaplayeraudio1!!.seekTo(0)
            } else {
                mediaplayeraudio1!!.start()
            }
        }
        binding.pauseaudio1.setOnClickListener{
            if(mediaplayeraudio1!!.isPlaying){
                mediaplayeraudio1!!.pause()
            }
        }

        //funcion para controlar audio2
        binding.playaudio2.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio2!!.isPlaying){
                mediaplayeraudio2!!.seekTo(0)
            } else {
                mediaplayeraudio2!!.start()
            }
        }
        binding.pauseaudio2.setOnClickListener{
            if(mediaplayeraudio2!!.isPlaying){
                mediaplayeraudio2!!.pause()
            }
        }
        //funcion para controlar audio3
        binding.playaudio3.setOnClickListener{
           pararaudios()
            if(mediaplayeraudio3!!.isPlaying){
                mediaplayeraudio3!!.seekTo(0)
            } else {
                mediaplayeraudio3!!.start()
            }
        }
        binding.pauseaudio3.setOnClickListener{
            if(mediaplayeraudio3!!.isPlaying){
                mediaplayeraudio3!!.pause()
            }
        }
        //funcion para controlar audio4
        binding.playaudio4.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio4!!.isPlaying){
                mediaplayeraudio4!!.seekTo(0)
            } else {
                mediaplayeraudio4!!.start()
            }
        }
        binding.pauseaudio4.setOnClickListener{
            if(mediaplayeraudio4!!.isPlaying){
                mediaplayeraudio4!!.pause()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaplayer_azal!!.pause()
        mediaplayeraudio1!!.pause()
        mediaplayeraudio2!!.pause()
        mediaplayeraudio3!!.pause()
        mediaplayeraudio4!!.pause()

    }
    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
    private fun pararaudios(){

        if (mediaplayeraudio1!!.isPlaying){
            mediaplayeraudio1!!.seekTo(0)
            mediaplayeraudio1!!.pause()
            }
       if (mediaplayeraudio2!!.isPlaying){
           mediaplayeraudio2!!.seekTo(0)
           mediaplayeraudio2!!.pause()
        }
        if (mediaplayeraudio3!!.isPlaying){
            mediaplayeraudio3!!.seekTo(0)
            mediaplayeraudio3!!.pause()
        } else if (mediaplayeraudio4!!.isPlaying){
            mediaplayeraudio4!!.seekTo(0)
            mediaplayeraudio4!!.pause()
        }



    }
}