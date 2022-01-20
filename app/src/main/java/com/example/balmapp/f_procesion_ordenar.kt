package com.example.balmapp

import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.balmapp.databinding.LProcesionJuegoOrdenarBinding


private var _binding: LProcesionJuegoOrdenarBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_ordenar : Fragment() {
    override fun onCreateView(
        //asignacion del layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoOrdenarBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NavFrag.atras_lugar="Juego2"
        NavFrag.animacion_dantzaris(binding.dantzarisProcesionOrdenar)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.azalpena_jokoa_procesion2)
        mediaplayer!!.start()
        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.dantzarisProcesionOrdenar)
        }
        //parar y continuar el audio
        binding.dantzarisProcesionOrdenar.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.dantzarisProcesionOrdenar)
                mediaplayer!!.pause()
            }else{
                if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
                    mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
                }
                mediaplayer!!.start()
                NavFrag.animacion_dantzaris(binding.dantzarisProcesionOrdenar)
            }
        }
        cargar_spinner()
        binding.btnprocesionordenar.setOnClickListener {
        comprobar()
        }
    }

    override fun onStop() {
        super.onStop()
        //liberacion del productor de medios
        mediaplayer?.release()
        mediaplayer = null
    }
    //Esta funcion nos cargara los datos en el spinner
    fun cargar_spinner(){
        val s0= resources.getString(R.string.elegir)
        val s1= resources.getString(R.string.camino)
        val s2= resources.getString(R.string.muerte)
        val s3= resources.getString(R.string.ultima)
        val s4= resources.getString(R.string.juicio)

        val lista = listOf(s0,s1,s2,s3,s4)
        val adaptador = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,lista)
        binding.spinner1.adapter=adaptador
        binding.spinner2.adapter=adaptador
        binding.spinner3.adapter=adaptador
        binding.spinner4.adapter=adaptador
    }
    //Esta funcion comprobara si los spinners estan bien ordenados
    //Si estan bien ordenados nos saldran en verde.
    //En caso de que alguno este mal ordenado nos aparecera el spinner en rojo
    fun comprobar(){
        val guru= resources.getString(R.string.camino)
        val judas= resources.getString(R.string.muerte)
        val azk= resources.getString(R.string.ultima)
        val jesus= resources.getString(R.string.juicio)
        var comprob= 1
        if(binding.spinner1.selectedItem.toString()==azk) {
            binding.spinner1.setBackgroundColor(Color.parseColor("#00ff00"))
        }else{
            binding.spinner1.setBackgroundColor(Color.parseColor("#ad0a15"))
            comprob=0
        }
        if(binding.spinner2.selectedItem.toString()==judas) {
            binding.spinner2.setBackgroundColor(Color.parseColor("#00ff00"))
        }else{
            binding.spinner2.setBackgroundColor(Color.parseColor("#ad0a15"))
            comprob=0
        }
        if(binding.spinner3.selectedItem.toString()==jesus) {
            binding.spinner3.setBackgroundColor(Color.parseColor("#00ff00"))
        }else{
            binding.spinner3.setBackgroundColor(Color.parseColor("#ad0a15"))
            comprob=0
        }
        if(binding.spinner4.selectedItem.toString()==guru) {
            binding.spinner4.setBackgroundColor(Color.parseColor("#00ff00"))
        }else{
            binding.spinner4.setBackgroundColor(Color.parseColor("#ad0a15"))
            comprob=0
        }

        //Cuando los spinners esten todos bien nos saltara el dialogo.
        if(comprob==1){
            Sharedapp.gune.gune="4.Gunea 2"
            //paramos el audio
            mediaplayer!!.stop()
            mostrarDialogoPersonalizado()
        }
    }

    //Esto hace saltar el dialogo para poder elegir volver al mapa o volver a jugar de nuevo.
    private fun mostrarDialogoPersonalizado(){
        AlertDialog.Builder(requireContext(), R.style.DialogBasicCustomStyle)
            .setView(layoutInflater.inflate(R.layout.l_dialogofindejuego,null))
            .setPositiveButton(R.string.txt_finalizar,
                DialogInterface.OnClickListener { dialog, id ->
                    NavFrag.IniciarActivity(requireContext(),"a_mapa")
                    requireActivity().finish()
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