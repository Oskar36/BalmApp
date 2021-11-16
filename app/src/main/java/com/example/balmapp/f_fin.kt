package com.example.balmapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.balmapp.databinding.LApodoBinding
import com.example.balmapp.databinding.LFinBinding



private var _binding: LFinBinding? = null
private val binding get() = _binding!!

class f_fin : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LFinBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(requireContext(), "${Sharedapp.gune.gune}", Toast.LENGTH_SHORT).show()
        binding.btnfinContinuar.setOnClickListener(){
            NavFrag.IniciarActivity(requireContext(),"a_mapa")
        }
        binding.btnfinRepetir.setOnClickListener(){
            val fragment:Fragment=f_partida()
            NavFrag.replaceFragment(fragment,requireActivity(),((view as ViewGroup).parent as View).id)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}