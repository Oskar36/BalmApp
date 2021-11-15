package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LFelipeCancionBinding


private var _binding: LFelipeCancionBinding? = null
private val binding get() = _binding!!
private var mediaPlayer: MediaPlayer?=null
class f_sanfelipe_cancion : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LFelipeCancionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btncancion.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }

    }
    override fun onResume() {
        super.onResume()
         mediaPlayer = MediaPlayer.create(context, R.raw.felipe_cancion)
       binding.play.setOnClickListener{
           if(mediaPlayer!!.isPlaying()){
               mediaPlayer!!.seekTo(0);
           } else {
               mediaPlayer!!.start()
           }
       }
        binding.pause.setOnClickListener{
            mediaPlayer!!.pause()
        }
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