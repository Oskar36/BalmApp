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

        binding.btnsiguiente.setOnClickListener{
            Sharedapp.gune.gune="5.Gunea"
            mostrarDialogoPersonalizado()


        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)


        //parar animacion cuando pare el audio
        //    mediaplayer!!.setOnCompletionListener {
        //   NavFrag.animacion_dantzaris_parar(binding.imglogo)        }
    }

    override fun onResume() {
        super.onResume()
        mediaplayeraudio5 = MediaPlayer.create(context, R.raw.txapelaaudio1)
        mediaplayeraudio6 = MediaPlayer.create(context, R.raw.txapelaaudio2)
        mediaplayeraudio7 = MediaPlayer.create(context, R.raw.txapelaaudio3)
        mediaplayeraudio8 = MediaPlayer.create(context, R.raw.txapelaaudio4)



        //funcion para controlar audio5
        binding.playaudio5.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio5!!.isPlaying){
                mediaplayeraudio5!!.seekTo(0)
            } else {
                mediaplayeraudio5!!.start()
            }
        }
        binding.pauseaudio5.setOnClickListener{
            if(mediaplayeraudio5!!.isPlaying){
                mediaplayeraudio5!!.pause()
            }
        }

        //funcion para controlar audio6
        binding.playaudio6.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio6!!.isPlaying){
                mediaplayeraudio6!!.seekTo(0)
            } else {
                mediaplayeraudio6!!.start()
            }
        }
        binding.pauseaudio6.setOnClickListener{
            if(mediaplayeraudio6!!.isPlaying){
                mediaplayeraudio6!!.pause()
            }
        }
        //funcion para controlar audio7
        binding.playaudio7.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio7!!.isPlaying){
                mediaplayeraudio7!!.seekTo(0)
            } else {
                mediaplayeraudio7!!.start()
            }
        }
        binding.pauseaudio7.setOnClickListener{
            if(mediaplayeraudio7!!.isPlaying){
                mediaplayeraudio7!!.pause()
            }
        }
        //funcion para controlar audio8
        binding.playaudio8.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio8!!.isPlaying){
                mediaplayeraudio8!!.seekTo(0)
            } else {
                mediaplayeraudio8!!.start()
            }
        }
        binding.pauseaudio8.setOnClickListener{
            if(mediaplayeraudio8!!.isPlaying){
                mediaplayeraudio8!!.pause()
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

        if (mediaplayeraudio5!!.isPlaying){
            mediaplayeraudio5!!.seekTo(0)
            mediaplayeraudio5!!.pause()
        }
        if (mediaplayeraudio6!!.isPlaying){
            mediaplayeraudio6!!.seekTo(0)
            mediaplayeraudio6!!.pause()
        }
        if (mediaplayeraudio7!!.isPlaying){
            mediaplayeraudio7!!.seekTo(0)
            mediaplayeraudio7!!.pause()
        } else if (mediaplayeraudio8!!.isPlaying){
            mediaplayeraudio8!!.seekTo(0)
            mediaplayeraudio8!!.pause()
        }



    }
}