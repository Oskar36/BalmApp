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
import com.example.balmapp.databinding.LTxapelaUnirBinding


private var _binding:LTxapelaUnirBinding? = null
private val binding get() = _binding!!
private var mediaplayeraudio1: MediaPlayer? = null
private var mediaplayeraudio2: MediaPlayer? = null
private var mediaplayeraudio3: MediaPlayer? = null
private var mediaplayeraudio4: MediaPlayer? = null
private var mediaplayer_azal: MediaPlayer? = null
private var linea: Linea? =null

class f_juego_txapela_unir : Fragment() {

    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LTxapelaUnirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.contador=4
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //parar animacion cuando pare el audio
        //mediaplayer!!.setOnCompletionListener {
        //NavFrag.animacion_dantzaris_parar(binding.imglogo)        }
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

        //el inicio y el final correcto de cada linea
        binding.txapelaimagen4.setOnClickListener{
            crearLinea(binding.txapelaimagen4,binding.txt1Unir, binding.scrollView5)
        }
        binding.txapelaimagen1.setOnClickListener{
            crearLinea(binding.txapelaimagen1,binding.txt2Unir,binding.scrollView6)
        }
        binding.txapelaimagen2.setOnClickListener{
            crearLinea(binding.txapelaimagen2,binding.txt3Unir,binding.scrollView7)
        }
        binding.txapelaimagen3.setOnClickListener{
            crearLinea(binding.txapelaimagen3,binding.txt4Unir,binding.scrollView8)
        }
    }

    //crea la linea teneiendo en cuenta el inicio y el final correcto
    private fun crearLinea(txtinicion: TextView, textfin:TextView,scrollView: ScrollView){
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
        linea!!.txapela=true
    }

    //crea los reproductores de audo y si esta sonando lo para y resetea
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
                mediaplayeraudio1!!.pause()
                mediaplayeraudio1!!.seekTo(mediaplayeraudio1!!.currentPosition)
            } else {
                mediaplayeraudio1!!.start()
            }
        }

        //funcion para controlar audio2
        binding.playaudio2.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio2!!.isPlaying){
                mediaplayeraudio2!!.pause()
                mediaplayeraudio2!!.seekTo(mediaplayeraudio2!!.currentPosition)
            } else {
                mediaplayeraudio2!!.start()
            }
        }

        //funcion para controlar audio3
        binding.playaudio3.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio3!!.isPlaying){
                mediaplayeraudio3!!.pause()
                mediaplayeraudio3!!.seekTo(mediaplayeraudio3!!.currentPosition)
            } else {
                mediaplayeraudio3!!.start()
            }
        }

        //funcion para controlar audio4
        binding.playaudio4.setOnClickListener{
            pararaudios()
            if(mediaplayeraudio4!!.isPlaying){
                mediaplayeraudio4!!.pause()
                mediaplayeraudio4!!.seekTo(mediaplayeraudio4!!.currentPosition)
            } else {
                mediaplayeraudio4!!.start()
            }
        }
    }
//para los audios
    override fun onDestroyView() {
        super.onDestroyView()
        mediaplayer_azal!!.pause()
        mediaplayeraudio1!!.pause()
        mediaplayeraudio2!!.pause()
        mediaplayeraudio3!!.pause()
        mediaplayeraudio4!!.pause()

    }
//para el audio que esta reproduciendo
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