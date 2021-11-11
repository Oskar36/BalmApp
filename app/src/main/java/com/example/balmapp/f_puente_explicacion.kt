package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LPuenteExplicacionBinding
import com.example.balmapp.databinding.LPuentePuzzleBinding


private var _binding: LPuenteExplicacionBinding? = null
private val binding get() = _binding!!

class f_puente_explicacion : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LPuenteExplicacionBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnpuenteExplicacionJugar.setOnClickListener(){
            val fragment:Fragment=f_puente_puzzle()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}