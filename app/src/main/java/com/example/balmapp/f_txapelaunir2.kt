package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.isGone
import com.example.balmapp.databinding.LTxapelaunir2Binding


private var _binding: LTxapelaunir2Binding? = null
private val binding get() = _binding!!
private var mediaplayeraudio5: MediaPlayer? = null
private var mediaplayeraudio6: MediaPlayer? = null
private var mediaplayeraudio7: MediaPlayer? = null
private var mediaplayeraudio8: MediaPlayer? = null
private var linea: Linea? =null
class f_txapelaunir2 : Fragment() {

    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LTxapelaunir2Binding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.contador=4
        NavFrag.atras_lugar="Juego2"
        Sharedapp.gune.gune="5.Gunea"
        //parar animacion cuando pare el audio
        //    mediaplayer!!.setOnCompletionListener {
        //   NavFrag.animacion_dantzaris_parar(binding.imglogo)        }


        //el inicio y el final correcto de cada linea
        binding.txapelaimagen8.setOnClickListener{
            crearLinea(binding.txapelaimagen8,binding.txt1Unir, binding.scrollView5)
        }
        binding.txapelaimagen6.setOnClickListener{
            crearLinea(binding.txapelaimagen6,binding.txt3Unir,binding.scrollView7)
        }
        binding.txapelaimagen7.setOnClickListener{
            crearLinea(binding.txapelaimagen7,binding.txt2Unir,binding.scrollView6)
        }
        binding.txapelaimagen.setOnClickListener{
            crearLinea(binding.txapelaimagen,binding.txt4Unir,binding.scrollView8)
        }

    }

    //crea la linea teneiendo en cuenta el inicio y el final correcto
    private fun crearLinea(txtinicion: TextView, textfin: TextView, scrollView: ScrollView){
        if(linea!=null && !NavFrag.terminado_unir){
            linea!!.isGone=true
        }
        linea=Linea(requireContext())
        binding.frameLayout7.addView(linea)
        linea!!.startX=txtinicion.x + txtinicion.width
        linea!!.startY=txtinicion.y + (txtinicion.height/2)
        linea!!.endX=txtinicion.x + txtinicion.width+30
        linea!!.endY=txtinicion.y + (txtinicion.height/2)
        linea!!.rbx=scrollView.x
        linea!!.rby=scrollView.y
        linea!!.scroll=scrollView
        //linea!!.txtfin=textfin
        linea!!.texto=txtinicion!!
        linea!!.layoutInflater=layoutInflater
        linea!!.activity=requireActivity()
        linea!!.view=((view as ViewGroup).parent as View)
        NavFrag.terminado_unir=false
    }
    override fun onResume() {
        super.onResume()
        mediaplayeraudio5 = MediaPlayer.create(context, R.raw.txapelaaudio5)
        mediaplayeraudio6 = MediaPlayer.create(context, R.raw.txapelaaudio6)
        mediaplayeraudio7 = MediaPlayer.create(context, R.raw.txapelaaudio7)
        mediaplayeraudio8 = MediaPlayer.create(context, R.raw.txapelaaudio8)
        //funcion para controlar audio5
        binding.playaudio5.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio5!!.isPlaying){
                mediaplayeraudio5!!.pause()
                mediaplayeraudio5!!.seekTo(mediaplayeraudio5!!.currentPosition)
            } else {
                mediaplayeraudio5!!.start()
            }
        }
        //funcion para controlar audio6
        binding.playaudio6.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio6!!.isPlaying){
                mediaplayeraudio6!!.pause()
                mediaplayeraudio6!!.seekTo(mediaplayeraudio6!!.currentPosition)
            } else {
                mediaplayeraudio6!!.start()
            }
        }

        //funcion para controlar audio7
        binding.playaudio7.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio7!!.isPlaying){
                mediaplayeraudio7!!.pause()
                mediaplayeraudio7!!.seekTo(mediaplayeraudio7!!.currentPosition)
            } else {
                mediaplayeraudio7!!.start()
            }
        }

        //funcion para controlar audio8
        binding.playaudio8.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio8!!.isPlaying){
                mediaplayeraudio8!!.pause()
                mediaplayeraudio8!!.seekTo(mediaplayeraudio8!!.currentPosition)
            } else {
                mediaplayeraudio8!!.start()
            }
        }

    }
//para los audios
    override fun onDestroyView() {
        super.onDestroyView()
        mediaplayeraudio5!!.pause()
        mediaplayeraudio6!!.pause()
        mediaplayeraudio7!!.pause()
        mediaplayeraudio8!!.pause()

    }
//para los audios que se esten reproduciendo
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