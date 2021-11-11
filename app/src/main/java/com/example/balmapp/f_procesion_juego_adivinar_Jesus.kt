package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LProcesionJuegoAdivinarAndremariabirjinaBinding
import com.example.balmapp.databinding.LProcesionJuegoAdivinarJesusBinding


private var _binding: LProcesionJuegoAdivinarJesusBinding? = null
private val binding get() = _binding!!
class f_procesion_juego_adivinar_Jesus : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizar.setOnClickListener(){
            val fragment:Fragment=f_procesion_juego_adivinarPenitenteak()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LProcesionJuegoAdivinarJesusBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}