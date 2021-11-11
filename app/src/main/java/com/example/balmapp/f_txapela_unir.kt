package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LTxapelaUnirBinding


private var _binding: LTxapelaUnirBinding? = null
private val binding get() = _binding!!
class f_juego_txapela_unir : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LTxapelaUnirBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnfinalizarPuente.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}