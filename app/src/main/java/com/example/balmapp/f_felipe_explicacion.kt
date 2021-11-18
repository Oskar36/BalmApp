package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LFelipeExplicacionBinding




private var _binding: LFelipeExplicacionBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_felipe_explicacion : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LFelipeExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfelipeExplicacionJugar.setOnClickListener(){
            val fragment:Fragment=f_sanfelipe_cancion()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
            //paramos el audio
            mediaplayer!!.stop()
        }
        NavFrag.animacion_dantzaris(binding.imgfelipeExplicacionLogo)


        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.sanfelipe_azalpena)


        //Toast.makeText(requireContext(), repro.toString(), Toast.LENGTH_SHORT).show()
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imgfelipeExplicacionLogo)        }

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