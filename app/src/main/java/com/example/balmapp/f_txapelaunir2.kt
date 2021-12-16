package com.example.balmapp

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LTxapelaUnirBinding
import com.example.balmapp.databinding.LTxapelaunir2Binding


private var _binding: LTxapelaunir2Binding? = null
private val binding get() = _binding!!
private var mediaplayeraudio5: MediaPlayer? = null
private var mediaplayeraudio6: MediaPlayer? = null
private var mediaplayeraudio7: MediaPlayer? = null
private var mediaplayeraudio8: MediaPlayer? = null

class f_txapelaunir2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LTxapelaunir2Binding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.atras_lugar="Juego2"
        binding.btnsiguiente.setOnClickListener {
            Sharedapp.gune.gune = "5.Gunea"
            mostrarDialogoPersonalizado()
        }



        //parar animacion cuando pare el audio
        //    mediaplayer!!.setOnCompletionListener {
        //   NavFrag.animacion_dantzaris_parar(binding.imglogo)        }
    }

    override fun onResume() {
        super.onResume()
        mediaplayeraudio5 = MediaPlayer.create(context, R.raw.txapelaaudio5)
        mediaplayeraudio6 = MediaPlayer.create(context, R.raw.txapelaaudio6)
        mediaplayeraudio7 = MediaPlayer.create(context, R.raw.txapelaaudio7)
        mediaplayeraudio8 = MediaPlayer.create(context, R.raw.txapelaaudio8)
        //funcion para controlar audio5
        binding.playaudio5.setOnClickListener{
            if(mediaplayeraudio5!!.isPlaying){
                mediaplayeraudio5!!.pause()
                mediaplayeraudio5!!.seekTo(mediaplayeraudio5!!.currentPosition)
            } else {
                mediaplayeraudio5!!.start()
            }
        }
        //funcion para controlar audio6
        binding.playaudio6.setOnClickListener{
            if(mediaplayeraudio6!!.isPlaying){
                mediaplayeraudio6!!.pause()
                mediaplayeraudio6!!.seekTo(mediaplayeraudio6!!.currentPosition)
            } else {
                mediaplayeraudio6!!.start()
            }
        }

        //funcion para controlar audio7
        binding.playaudio7.setOnClickListener{
            if(mediaplayeraudio7!!.isPlaying){
                mediaplayeraudio7!!.pause()
                mediaplayeraudio7!!.seekTo(mediaplayeraudio7!!.currentPosition)
            } else {
                mediaplayeraudio7!!.start()
            }
        }

        //funcion para controlar audio8
        binding.playaudio8.setOnClickListener{
            if(mediaplayeraudio8!!.isPlaying){
                mediaplayeraudio8!!.pause()
                mediaplayeraudio8!!.seekTo(mediaplayeraudio8!!.currentPosition)
            } else {
                mediaplayeraudio8!!.start()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaplayeraudio5!!.pause()
        mediaplayeraudio6!!.pause()
        mediaplayeraudio7!!.pause()
        mediaplayeraudio8!!.pause()

    }

    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    requireActivity().finish()
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

}