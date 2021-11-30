package com.example.balmapp

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LTxapelaUnirBinding


private var _binding: LTxapelaUnirBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null
class f_juego_txapela_unir : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LTxapelaUnirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnfinalizarPuente.setOnClickListener{
            Sharedapp.gune.gune="5.Gunea"
            mostrarDialogoPersonalizado()

        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_unir_txapela)
        //Iniciamos el audio
        mediaplayer!!.start()

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
    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofin(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
<<<<<<< HEAD
    private fun pararaudios(){

        if (mediaplayeraudio1!!.isPlaying){
            mediaplayeraudio1!!.seekTo(0)
            mediaplayeraudio1!!.pause()
            }
       if (mediaplayeraudio2!!.isPlaying){
           mediaplayeraudio2!!.seekTo(0)
           mediaplayeraudio2!!.pause()
        }
        if (mediaplayeraudio3!!.isPlaying){
            mediaplayeraudio3!!.seekTo(0)
            mediaplayeraudio3!!.pause()
        } else if (mediaplayeraudio4!!.isPlaying){
            mediaplayeraudio4!!.seekTo(0)
            mediaplayeraudio4!!.pause()
        }



    }
=======
>>>>>>> b42d944986ffc3a13fd9e4ed9ec4fb6e88fe6e1d
}