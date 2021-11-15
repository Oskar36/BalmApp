package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LProcesionJuegoOrdenarBinding
import com.example.balmapp.databinding.LPuentePuzzleBinding


private var _binding: LProcesionJuegoOrdenarBinding? = null
private val binding get() = _binding!!

class f_procesion_ordenar : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoOrdenarBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnprocesionordenar.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }
        //inicio de la animacion
        NavFrag.animacion_dantzaris(binding.dantzarisProcesionOrdenar)

        //parar animacion cuando pare el audio
        mediaplayer!!.setOnCompletionListener {
            NavFrag.animacion_dantzaris_parar(binding.dantzarisProcesionOrdenar)        }
    }
}