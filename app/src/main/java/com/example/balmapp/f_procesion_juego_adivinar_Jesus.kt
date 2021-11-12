package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
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

    // TODO: Rename and change types of parameters

    private var mediaplayer: MediaPlayer? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener(){
            val fragment:Fragment=f_procesion_juego_adivinarPenitenteak()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }

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
        mediaPlayer!!.stop()
    }

    override fun onResume() {

        super.onResume()


        mediaPlayer = MediaPlayer.create(context, R.raw.jesus)
        binding.imgaudioplay1.setOnClickListener{

            if(mediaPlayer!!.isPlaying()){
                mediaPlayer!!.seekTo(0)
            } else {
                mediaPlayer!!.start()
            }
        }
        binding.imgaudiostop1.setOnClickListener{
            mediaPlayer!!.pause()
        }
    }

    override fun onStart() {
        super.onStart()


    }


    }


