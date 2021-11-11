package com.example.balmapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LModoBinding
import com.example.balmapp.databinding.LPartidaBinding


private var _binding: LPartidaBinding? = null
private val binding get() = _binding!!
class f_partida : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      binding.btnpartidaNueva.setOnClickListener(){
          NavFrag.IniciarActivity(requireContext(),"a_mapa")
      }
        binding.btnpartidaContinuar.setOnClickListener(){

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LPartidaBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
