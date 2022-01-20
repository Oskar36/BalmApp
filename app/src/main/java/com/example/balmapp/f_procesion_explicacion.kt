package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LProcesionExplicacionBinding
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer

lateinit var animacion : AnimationDrawable
private var _binding: LProcesionExplicacionBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_procesion_explicacion : Fragment() {

    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //Clickando en el boton, nos llevara al primer juego, en este caso ADIVINAR
        super.onActivityCreated(savedInstanceState)
        binding.btnprocesionExplicacionJugar.setOnClickListener{
            NavFrag.atras_lugar="Juego1"
            val fragment:Fragment=f_procesion_juego_adivinar_Jesus()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Explicacion")
            //paramos el audio
            mediaplayer!!.stop()

        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgprocesionExplicacionLogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_precesion)

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgprocesionExplicacionLogo)
        }


        //parar y continuar el audio
        binding.imgprocesionExplicacionLogo.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imgprocesionExplicacionLogo)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imgprocesionExplicacionLogo)
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onStart() {
        super.onStart()
        //Iniciamos el audio
        mediaplayer!!.start()
    }
    override fun onStop() {
        super.onStop()
        //liberacion del productor de medios
        mediaplayer?.release()
        mediaplayer = null
    }
}