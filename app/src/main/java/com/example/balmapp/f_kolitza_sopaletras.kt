package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LKolitzaSopaletrasBinding


private var _binding: LKolitzaSopaletrasBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_kolitza_juego_sopaletras : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LKolitzaSopaletrasBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnsiguienteJuego.setOnClickListener{
            //paramos el audio
            mediaplayer!!.stop()
            Sharedapp.gune.gune="2.Gunea 1"
            val fragment:Fragment=f_fin_intermedio()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"SopaLetras","Explicacion")
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_kolitza)


        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)        }

        //parar y continuar el audio
        binding.imglogo.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imglogo)
                mediaplayer!!.stop()
            }else{
                mediaplayer!!.prepare()
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.imglogo)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
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