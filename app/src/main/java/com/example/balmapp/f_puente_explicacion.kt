package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LPuenteExplicacionBinding
import com.example.balmapp.databinding.LPuentePuzzleBinding


private var _binding: LPuenteExplicacionBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_puente_explicacion : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LPuenteExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnpuenteExplicacionJugar.setOnClickListener(){
            val fragment:Fragment=f_puente_puzzle()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
            //paramos el audio
            mediaplayer!!.stop()

        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imgpuenteExplicacionLogo)

        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.zubia_azalpena)




        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgpuenteExplicacionLogo)        }

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