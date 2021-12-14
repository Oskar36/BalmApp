package com.example.balmapp

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LApodoBinding

import com.example.balmapp.databinding.LJauregiUnirBinding


private var _binding: LJauregiUnirBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_jauregi_unirjuego : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LJauregiUnirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.imglogo)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_jauregi_unir)
        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.imglogo)
        }
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
        binding.btnfinalizarjauregi.setOnClickListener {
            //Abrir fragment repetir juego
                mediaplayer!!.stop()
            Sharedapp.gune.gune="3.Gunea 2"
            SystemClock.sleep(1000)
            mostrarDialogoPersonalizado()
        }

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
}