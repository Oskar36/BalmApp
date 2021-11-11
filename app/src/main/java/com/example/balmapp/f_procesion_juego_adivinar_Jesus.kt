package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LProcesionJuegoAdivinarAndremariabirjinaBinding
import com.example.balmapp.databinding.LProcesionJuegoAdivinarJesusBinding


private var _binding: LProcesionJuegoAdivinarJesusBinding? = null
private val binding get() = _binding!!
class f_procesion_juego_adivinar_Jesus : Fragment() {
<<<<<<< HEAD
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mediaplayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }





=======

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
>>>>>>> c5c11f91d5c4710a3dc308f3562a4a86b89b0553
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener(){
            val fragment:Fragment=f_procesion_juego_adivinarPenitenteak()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.Jesus)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarJesusBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {

        super.onResume()


        binding.imgaudioplay1.setOnClickListener(){
            //Iniciamos el audio
            mediaplayer!!.start();
        }
        binding.imgaudiostop1.setOnClickListener(){
            //Pausamos el audio
            mediaplayer!!.pause();
        }

    }

    override fun onStart() {
        super.onStart()

    }


    }


