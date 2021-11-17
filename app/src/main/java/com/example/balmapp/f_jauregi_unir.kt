package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LJauregiUnirBinding


private var _binding: LJauregiUnirBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_jauregi_unirjuego : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LJauregiUnirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_jauregi_unir)
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        binding.btnfinalizarjauregi.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            //paramos el audio
            mediaplayer!!.stop()
        }
        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)
        }
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

        binding.btnfinalizarjauregi.setOnClickListener {
            //Abrir fragment repetir juego
            Sharedapp.gune.gune="3.Gunea 2"
            val fragment:Fragment=f_fin()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
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