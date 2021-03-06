package com.example.balmapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LJauregiExplicacionBinding


private var _binding: LJauregiExplicacionBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_jauregi_explicacion : Fragment() {
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LJauregiExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //Cuando clickemos en el boton JUGAR nos llevara al puzzle
        super.onActivityCreated(savedInstanceState)
        NavFrag.atras_lugar="Juego1"
        binding.btnfinalizarPuente.setOnClickListener{
            val fragment:Fragment=f_jauregi_puzzle()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id,"Explicacion")
            //paramos el audio
            mediaplayer!!.stop()
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.jauregi_azalpena)



        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)        }


        //parar y continuar el audio
        binding.imglogo.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.imglogo)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
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