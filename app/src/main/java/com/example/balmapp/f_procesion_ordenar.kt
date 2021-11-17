package com.example.balmapp

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.balmapp.databinding.LProcesionJuegoOrdenarBinding


private var _binding: LProcesionJuegoOrdenarBinding? = null
private val binding get() = _binding!!
private var mediaplayer: MediaPlayer? = null

class f_procesion_ordenar : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoOrdenarBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        NavFrag.animacion_dantzaris(binding.dantzarisProcesionOrdenar)
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        mediaplayer = MediaPlayer.create(context, R.raw.kolitza_azalpena)
        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.dantzarisProcesionOrdenar)
        }
        //parar y continuar el audio
        binding.dantzarisProcesionOrdenar.setOnClickListener {
            if(mediaplayer!!.isPlaying){
                NavFrag.animacion_dantzaris_parar(binding.dantzarisProcesionOrdenar)
                mediaplayer!!.stop()
            }else{

                mediaplayer!!.prepare()
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
    override fun onStart() {
        super.onStart()
        //Iniciamos el audio
        mediaplayer!!.start()
    }

    fun cargar_spinner(){
        val s0= "Eligir una opcion"
        val s1= binding.texto1.text
        val s2= binding.texto2.text
        val s3= binding.texto3.text
        val s4= binding.texto4.text

        val lista = listOf(s0,s1,s2,s3,s4)
        val adaptador = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,lista)
        binding.spinner1.adapter=adaptador
        binding.spinner2.adapter=adaptador
        binding.spinner3.adapter=adaptador
        binding.spinner4.adapter=adaptador
    }
    fun comprobar(){


        val guru= binding.texto1.text
        val judas= binding.texto2.text
        val azk= binding.texto3.text
        val jesus= binding.texto4.text
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




        if(comprob==1){
            Sharedapp.gune.gune="4.Gunea 2"
            //paramos el audio
            mediaplayer!!.stop()
            val fragment:Fragment=f_fin()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }


    }
}