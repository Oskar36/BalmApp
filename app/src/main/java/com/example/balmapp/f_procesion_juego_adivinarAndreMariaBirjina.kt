package com.example.balmapp

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AlertDialog

import android.widget.Toast

import com.example.balmapp.databinding.LProcesionJuegoAdivinarAndremariabirjinaBinding


private var _binding: LProcesionJuegoAdivinarAndremariabirjinaBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_juego_adivinarAndreMariaBirjina : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarAndremariabirjinaBinding.inflate(inflater, container, false)
        return  binding.root

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener{

            if (binding.mariaRadio.isChecked) {
                Sharedapp.gune.gune="4.Gunea 1"
                mostrarDialogoPersonalizado()

            }else{
                Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onResume() {

        super.onResume()
        mediaplayer = MediaPlayer.create(context, R.raw.andremariabirjina)
        binding.imgaudioplay1.setOnClickListener{

            if(mediaplayer!!.isPlaying){
                mediaplayer!!.seekTo(0)
            } else {
                mediaplayer!!.start()
            }
        }
        binding.imgaudiostop1.setOnClickListener{
            if(mediaplayer!!.isPlaying){
                mediaplayer!!.pause()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaplayer!!.stop()
    }
    private fun mostrarDialogoPersonalizado(){

        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_siguientejuego,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.AbrirSiguiente(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setNeutralButton(R.string.repetir,
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment:Fragment=NavFrag.MarcadorJuegofinintermedio(Sharedapp.gune.gune)
                    NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
                    // sign in the user ...
                })
            .setCancelable(false)
            .create()
            .show()

    }
}