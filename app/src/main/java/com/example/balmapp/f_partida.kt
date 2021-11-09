package com.example.balmapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balmapp.databinding.LModoBinding
import com.example.balmapp.databinding.LPartidaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private var _binding: LPartidaBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
class a_partida : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       /*
        binding.btnmodoLibre.setOnClickListener(){
            val intent= Intent(activity, a_mapa::class.java)
            activity?.finish()
            startActivity(intent)
        }
        binding.btnmodoGuiado.setOnClickListener(){

        }

        */
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LPartidaBinding.inflate(inflater, container, false)
        return  binding.root
    }
}