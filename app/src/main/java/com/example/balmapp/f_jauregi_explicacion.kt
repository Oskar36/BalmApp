package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LJauregiExplicacionBinding


private var _binding: LJauregiExplicacionBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_jauregi_explicacion : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LJauregiExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizarPuente.setOnClickListener(){
            val fragment:Fragment=f_jauregi_puzzle()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.jauregi_azalpena)
        //Iniciamos el audio
        mediaplayer!!.start()

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
    }
}