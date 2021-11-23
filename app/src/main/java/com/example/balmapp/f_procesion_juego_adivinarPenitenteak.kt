package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.balmapp.databinding.LProcesionJuegoAdivinarPenitenteakBinding

private var _binding: LProcesionJuegoAdivinarPenitenteakBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_juego_adivinarPenitenteak : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarPenitenteakBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener {
            if (binding.penitenteRadio.isChecked) {
                val fragment: Fragment = f_procesion_juego_adivinarAndreMariaBirjina()
                NavFrag.replaceFragment(
                    fragment,
                    requireActivity(),
                    ((view as ViewGroup).parent as View).id
                )
            }else{
                Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onResume() {

        super.onResume()
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.penitenteak)
        binding.imgaudioplay1.setOnClickListener{
        //controlamos que el audio no esta reproduciendose
            if(mediaplayer!!.isPlaying){
                mediaplayer!!.seekTo(0)
            } else {
                //Iniciamos el audio
                mediaplayer!!.start()
            }
        }
        binding.imgaudiostop1.setOnClickListener{
            //Pausamos el audio
            mediaplayer!!.pause()
        }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaplayer!!.stop()
    }
}