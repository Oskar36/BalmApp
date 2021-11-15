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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LJauregiUnirBinding.inflate(inflater, container, false)
        return  binding.root
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_jauregi_unir)



        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizarjauregi.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
            //paramos el audio
            mediaplayer!!.stop()
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
        mediaPlayer?.release()
        mediaplayer = null


    }
}